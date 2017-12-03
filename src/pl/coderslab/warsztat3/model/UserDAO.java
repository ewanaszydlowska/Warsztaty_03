package pl.coderslab.warsztat3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public static void saveToDB(Connection conn, User u) throws SQLException {
		if(u.getId() == 0l) {
			String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?);";
			String[] generatedColumns = {"ID"};			//jaka kolumna z bazy danych jest automatycznie generowana -> jest auto_increment
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getUserGroupId());
			ps.executeUpdate();						//prepared statement trzyma w sobie dane ktore pobiera z bazy danych
			ResultSet gk = ps.getGeneratedKeys();	//baza zwraca wyniki w postaci resultset (wskazuje na przed pierwszą daną) rekordy zwrocone przez baze
			
			if (gk.next()) {				//przestawia na właściwe id
				u.setId(gk.getLong(1)); //kolumna 1 tabeli users
			}
			ps.close();
			gk.close();
		} else {
			String sql = "UPDATE users SET username=?, email=?, password=?, user_group_id=? WHERE id=?;"; //moze nowe preparedstatement?
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getUserGroupId());
			ps.setLong(5, u.getId());
			ps.executeUpdate();
			ps.close();
		}
	}
	
	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			User loadedUser = new User();
			loadedUser.setId(rs.getLong("id"));
			loadedUser.setUsername(rs.getString("username"));
			loadedUser.setEmail(rs.getString("email"));
			loadedUser.setPassword(rs.getString("password"));
			loadedUser.setUserGroupId(rs.getInt("user_group_id"));
			ps.close();
			rs.close();
			return loadedUser;
		}
		ps.close();
		return null;
	}
	
	static public User[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			User loadedUser = new User();
			loadedUser.setId(rs.getLong("id"));
			loadedUser.setUsername(rs.getString("username"));
			loadedUser.setEmail(rs.getString("email"));
			loadedUser.setPassword(rs.getString("password"));
			loadedUser.setUserGroupId(rs.getInt("user_group_id"));
			users.add(loadedUser);
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		ps.close();
		rs.close();
		return uArray;
	}
	
	public static void deleteUser(Connection conn, int id) throws SQLException {
			String sql = "DELETE FROM users WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
	}
	
	public static List<User> loadAllByGroupId(Connection conn, int id) throws SQLException {
		List<User> members = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE user_group_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			User u = new User();
			u.setId(rs.getLong("id"));
			u.setUsername(rs.getString("username"));
			u.setEmail(rs.getString("email"));
			u.setPassword(rs.getString("password"));
			u.setUserGroupId(rs.getInt("user_group_id"));
			members.add(u);
		}
		ps.close();
		rs.close();
		return members;
	}
	
}
