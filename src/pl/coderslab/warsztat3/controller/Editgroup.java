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
import pl.coderslab.warsztat3.model.Group;
import pl.coderslab.warsztat3.model.GroupDAO;

/**
 * Servlet implementation class Editgroup
 */
@WebServlet("/Editgroup")
public class Editgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editgroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int groupId = Integer.parseInt(request.getParameter("id"));
		 request.setAttribute("id", groupId);
		 getServletContext().getRequestDispatcher("/WEB-INF/editgroup.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupName = request.getParameter("group-name");
		int groupId = Integer.parseInt(request.getParameter("id"));
		try {
			Connection conn = DBUtil.getConn();
			Group g = new Group(groupName);
			g.setId(groupId);
			
			GroupDAO.saveToDB(conn, g);
			conn.close();
			response.sendRedirect("groups");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
