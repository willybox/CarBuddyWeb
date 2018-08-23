package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.User;
import fr.carbuddyweb.global.ReadOnlyGlobal;
import util.library.add.on.date.AddOnDate;

public class UserSessionProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException{
		/** Getting connected user */
		User u = (User) request.getSession().getAttribute(ReadOnlyGlobal.USER_SESSION);
		
    	request.setAttribute("age", AddOnDate.getAge(u.getBirthday()));
    	
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/MyProfile.jsp")
			.forward(request, response);
    }

}
