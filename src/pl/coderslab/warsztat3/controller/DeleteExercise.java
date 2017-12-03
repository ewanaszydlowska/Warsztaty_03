package pl.coderslab.warsztat3.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.warsztat3.db.DBUtil;
import pl.coderslab.warsztat3.model.Exercise;
import pl.coderslab.warsztat3.model.ExerciseDAO;

/**
 * Servlet implementation class DeleteExercise
 */
@WebServlet("/Deleteexercise")
public class DeleteExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteExercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int exeId = Integer.parseInt(request.getParameter("id"));

		Connection conn;
		try {
			conn = DBUtil.getConn();
			ExerciseDAO.deleteExercise(conn, exeId);
			Exercise[] exercises = ExerciseDAO.loadAllExercises(conn);
			conn.close();
			request.setAttribute("exercises", exercises);
			getServletContext().getRequestDispatcher("/WEB-INF/exercisespanel.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
