package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddyweb.form.UserInscriptionForm;

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
		
		//TODO to do
		//request.authenticate(arg0)
		request.setAttribute("dummyHello", "slt abruti :)");
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/index.jsp")
			.forward(request, response);
    }

}
