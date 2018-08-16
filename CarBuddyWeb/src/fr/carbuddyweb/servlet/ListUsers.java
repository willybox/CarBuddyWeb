package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.string.StatusUser;
import util.library.add.on.date.AddOnDate;

public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException{
		
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/ListUsers.jsp")
			.forward(request, response);
    }

}
