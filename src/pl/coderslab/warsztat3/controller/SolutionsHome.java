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
import pl.coderslab.warsztat3.model.Solution;
import pl.coderslab.warsztat3.model.SolutionDAO;

/**
 * Servlet implementation class SolutionsHome
 */
@WebServlet("/Solution")
public class SolutionsHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolutionsHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int solutionId = Integer.parseInt(request.getParameter("id"));
		try {
			Connection conn = DBUtil.getConn();
			Solution s = SolutionDAO.loadSolutionById(conn, solutionId);
			request.setAttribute("solution", s);
			getServletContext().getRequestDispatcher("/WEB-INF/solution.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
