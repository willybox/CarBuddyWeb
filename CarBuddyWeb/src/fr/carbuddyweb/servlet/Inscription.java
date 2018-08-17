package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddyweb.form.UserInscriptionForm;
import fr.carbuddyweb.global.ReadOnlyGlobal;

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory daoFactory;

	@Override
    public void init() throws ServletException {
        this.daoFactory = ((DAOFactory) getServletContext()
        	.getAttribute(ReadOnlyGlobal.ATT_DAO_FACTORY));
    }
    
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
		User newUser = new UserInscriptionForm(request, daoFactory).newUser();
		/** Safely disconnect after all operation done */
		daoFactory.disconnect();
		if(newUser != null) {
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
