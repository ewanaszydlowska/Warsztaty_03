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
import pl.coderslab.warsztat3.model.User;
import pl.coderslab.warsztat3.model.UserDAO;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/Edituser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupId = Integer.parseInt(request.getParameter("id"));
		 request.setAttribute("id", groupId);
		 getServletContext().getRequestDispatcher("/WEB-INF/edituser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("user-name");
		String userEmail = request.getParameter("user-email");
		String userPasswd = request.getParameter("user-passwd");
		int userGroupId = Integer.parseInt("user-groupid");
		try {
			Connection conn = DBUtil.getConn();
			User u = new User(userName, userEmail, userPasswd);
			u.setId(userId);
			u.setUserGroupId(userGroupId);
			UserDAO.saveToDB(conn, u);
			conn.close();
			response.sendRedirect("users");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
