package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.global.ConstantValues;
import fr.carbuddyweb.form.UserConnectionForm;
import fr.carbuddyweb.global.ReadOnlyGlobal;

public class UserConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory daoFactory;

	@Override
    public void init() throws ServletException {
        this.daoFactory = ((DAOFactory) getServletContext()
        	.getAttribute(ConstantValues.ATT_DAO_FACTORY));
    }
	
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
		
		HttpSession session = request.getSession();
		User user = new UserConnectionForm(request, daoFactory).getUser();
		/** Safely disconnect factory after all operation done */
		daoFactory.disconnect();
		if(user == null) {
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/Connection.jsp")
				.forward(request, response);
		} else {
			session.setAttribute(ReadOnlyGlobal.USER_SESSION, user);
			response.sendRedirect( request.getContextPath() + "/session/index" );
		}
    }

}
