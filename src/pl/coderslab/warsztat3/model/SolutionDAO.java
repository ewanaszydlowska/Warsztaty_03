package pl.coderslab.warsztat3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDAO {
	
	public void saveToDB(Connection conn, Solution s) throws SQLException {
		if(s.getId() == 0l) {
			String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (NOW(), NOW(), ?, ?, ?);";
			String[] generatedColumns = {"ID"};		
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, s.getDescription());
			ps.setInt(2, s.getExerciseId());
			ps.setLong(3, s.getUsersId());
			ps.executeUpdate();						
			ResultSet gk = ps.getGeneratedKeys();	
			
			if (gk.next()) {	
				s.setId(gk.getInt(1));
			}
			ps.close();
			gk.close();
			
			Solution tempSolution = SolutionDAO.loadSolutionById(conn, s.getId());
			s.setCreated(tempSolution.getCreated());
			s.setUpdated(tempSolution.getUpdated());
			
		} else {
			String sql = "UPDATE solution SET updated=NOW(), description=?, exercise_id=?, users_id=? WHERE id=?;"; //moze nowe preparedstatement?
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getDescription());
			ps.setInt(2, s.getExerciseId());
			ps.setLong(3, s.getUsersId());
			ps.setInt(4, s.getId());
			ps.executeUpdate();
			ps.close();
			
			Solution tempSolution = SolutionDAO.loadSolutionById(conn, s.getId());
			s.setUpdated(tempSolution.getUpdated());
		}
	}
	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.setId(rs.getInt("id"));
			loadedSolution.setCreated(rs.getDate("created"));
			loadedSolution.setUpdated(rs.getDate("updated"));
			loadedSolution.setDescription(rs.getString("description"));
			loadedSolution.setExerciseId(rs.getInt("exercise_id"));
			loadedSolution.setUsersId(rs.getLong("users_id"));
			ps.close();
			rs.close();
			return loadedSolution;
		}
		ps.close();
		return null;
	}
	
	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.setId(rs.getInt("id"));
			loadedSolution.setCreated(rs.getDate("created"));
			loadedSolution.setUpdated(rs.getDate("updated"));
			loadedSolution.setDescription(rs.getString("description"));
			loadedSolution.setExerciseId(rs.getInt("exercise_id"));
			loadedSolution.setUsersId(rs.getLong("users_id"));
			solutions.add(loadedSolution);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		ps.close();
		rs.close();
		return sArray;
	}
	
	public void deleteSolution(Connection conn, Solution s) throws SQLException {
		if (s.getId() != 0) {
			String sql = "DELETE FROM solution WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getId());
			ps.executeUpdate();
			ps.close();
			s.setId(0);
		}
	}
	
	public static void loadAllByExerciseId (Connection conn, int id) throws SQLException {
		ArrayList<String> solutions = new ArrayList<String>();
		String beginList = "Rozwiazania zadania o id " + id + ":";
		solutions.add(beginList);
		String sql = "SELECT description FROM solution WHERE exercise_id=? ORDER BY updated DESC;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String desc = rs.getString("description");
			solutions.add(" * " + desc);
		}
		ps.close();
		rs.close();
		
		String[] sArray = new String[solutions.size()];
		sArray = solutions.toArray(sArray);
		for (int i = 0; i < sArray.length; i++) {
			System.out.println(sArray[i]);
		}
	}
	
	public static List<Solution> loadArrayByExerciseId (Connection conn, int id) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<>();
		String sql = "SELECT * FROM solution WHERE exercise_id=? ORDER BY updated DESC;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.setId(rs.getInt("id"));
			loadedSolution.setCreated(rs.getDate("created"));
			loadedSolution.setUpdated(rs.getDate("updated"));
			loadedSolution.setDescription(rs.getString("description"));
			loadedSolution.setExerciseId(rs.getInt("exercise_id"));
			loadedSolution.setUsersId(rs.getLong("users_id"));
			solutions.add(loadedSolution);
		}
		ps.close();
		rs.close();
		
		return solutions;
	}
	
	public static boolean checkIfExists(Connection conn, int exId, long userId) throws SQLException {
		String sql = "SELECT * FROM solution WHERE exercise_id=? AND users_id=? ORDER BY updated DESC;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, exId);
		ps.setLong(2, userId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
