package fr.carbuddyweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.enumeration.order.by.UserOrderBy;
import fr.carbuddy.global.ConstantValues;

@WebServlet("/session/search/Buddies")
public class SearchBuddies extends HttpServlet {
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
	) throws ServletException, IOException{
		List<User> buddies = daoFactory.getUserDAO().listBuddies(UserOrderBy.NAME, true);
		
    	request.setAttribute("buddies", buddies);
		
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/search/Buddies.jsp")
			.forward(request, response);
    }

}
