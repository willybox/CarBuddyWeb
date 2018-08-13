package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddyweb.form.UserInscriptionForm;

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/Inscription.jsp")
			.forward(request, response);
    }
	
	@Override
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		if(new UserInscriptionForm(request).newUser() != null) {
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/ValidationInscription.jsp")
				.forward(request, response);
		} else {
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/Inscription.jsp")
				.forward(request, response);
		}
    }

}
