package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.carbuddy.bean.User;

public class UserConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/Connection.jsp")
			.forward(request, response);
    }
	
	@Override
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		//TODO Retrieve User
		//request.authenticate(arg0)
		
		HttpSession session = request.getSession();
		//Dummy fake!
		User dummy = new User();
		session.setAttribute("user", dummy);
		
		// close session
		// session.invalidate();
		response.sendRedirect( request.getContextPath() + "/session/index" );
    }

}
