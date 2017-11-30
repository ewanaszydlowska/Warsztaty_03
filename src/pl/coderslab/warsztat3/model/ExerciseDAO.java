package pl.coderslab.warsztat3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDAO {

	public void saveToDB(Connection conn, Exercise e) throws SQLException {
		if (e.getId() == 0) {
			String sql = "INSERT INTO exercise(name, description) VALUES (?, ?);";
			String[] generatedColumns = {"id"};
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, e.getName());
			ps.setString(1, e.getDescription());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {
				e.setId(rs.getInt(1));
			}
			ps.close();
			rs.close();
		} else {
			String sql = "UPDATE exercise SET name=?, description=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setString(1, e.getDescription());
			ps.executeUpdate();
			ps.close();
		}
	}
	
	static public Exercise loadExerciseById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM exercise WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Exercise loadedEx = new Exercise();
			loadedEx.setId(rs.getInt("id"));
			loadedEx.setName(rs.getString("name"));
			loadedEx.setDescription(rs.getString("description"));
			ps.close();
			rs.close();
			return loadedEx;
		}
		ps.close();
		return null;
	}
	
	static public Exercise[] loadAllExercises(Connection conn) throws SQLException {
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		String sql = "SELECT * FROM exercise;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Exercise loadedEx = new Exercise();
			loadedEx.setId(rs.getInt("id"));
			loadedEx.setName(rs.getString("name"));
			loadedEx.setDescription(rs.getString("description"));
			exercises.add(loadedEx);
		}
		Exercise[] eArray = new Exercise[exercises.size()];
		eArray = exercises.toArray(eArray);
		ps.close();
		rs.close();
		return eArray;
	}
	
	public void deleteExercise(Connection conn, Exercise e) throws SQLException {
		if (e.getId() != 0) {
			String sql = "DELETE FROM exercise WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ps.executeUpdate();
			ps.close();
			e.setId(0);
		}
		
	}
	
	public static void loadAllByUserId(java.sql.Connection conn, long id) throws SQLException {
		ArrayList<String> solutions = new ArrayList<String>();
		String sql = "SELECT exercise.id, solution.description FROM exercise JOIN solution ON exercise.id = solution.exercise_id "
				+ "WHERE solution.users_id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int exerciseId = rs.getInt("exercise.id");
			String exercise = Integer.toString(exerciseId);
			String solutionDesc = rs.getString("solution.description");
			solutions.add("zadanie " + exercise + ". " + solutionDesc);
		}
		ps.close();
		rs.close();

		String[] sArray = new String[solutions.size()];
		sArray = solutions.toArray(sArray);
		for (int i = 0; i < sArray.length; i++) {
			System.out.println(sArray[i]);
		}
		
	}
}
