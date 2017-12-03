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
 * Servlet implementation class Addexercise
 */
@WebServlet("/Addexercise")
public class Addexercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addexercise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/addexercise.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exeName = request.getParameter("exe-name");
		String exeDesc = request.getParameter("exe-desc");
		try {
			Connection conn = DBUtil.getConn();
			Exercise exercise = new Exercise(exeName, exeDesc);
			ExerciseDAO.saveToDB(conn, exercise);
			conn.close();
			response.sendRedirect("exercises");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
