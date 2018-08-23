package fr.carbuddyweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.global.ConstantValues;

public class ListUsers extends HttpServlet {
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
		
		List<User> users = daoFactory.getUserDAO().listUser(null, true);
		
		request.setAttribute("users", users);
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/ListUsers.jsp")
			.forward(request, response);
    }

}
