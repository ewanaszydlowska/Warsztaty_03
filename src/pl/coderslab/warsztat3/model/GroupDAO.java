package pl.coderslab.warsztat3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDAO {

	public static void saveToDB(Connection conn, Group g) throws SQLException {
		if (g.getId() == 0) {
			String sql = "INSERT INTO user_group(name) VALUES(?);";
			String[] generatedColumns = {"id"};
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, g.getName());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {
				g.setId(rs.getInt(1));
			}
			ps.close();
			rs.close();
		} else {
			String sql = "UPDATE user_group SET name=? WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, g.getName());
			ps.setInt(2, g.getId());
			ps.executeUpdate();
			ps.close();
		}
	}
	
	public static Group loadGroupById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.setId(rs.getInt("id"));
			loadedGroup.setName(rs.getString("name"));
			return loadedGroup;
		}
		ps.close();
		rs.close();
		return null;
	}
	
	public static Group[] loadAllGroups(Connection conn) throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		String sql = "SELECT * FROM user_group;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.setId(rs.getInt("id"));
			loadedGroup.setName(rs.getString("name"));
			groups.add(loadedGroup);
		}
		ps.close();
		rs.close();
		Group[] gArray = new Group[groups.size()];
		gArray = groups.toArray(gArray);
		return gArray;
	}
	
	public static void deleteGroup(Connection conn, int id) throws SQLException {
			String sql = "DELETE FROM user_group WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
	}
	
}
