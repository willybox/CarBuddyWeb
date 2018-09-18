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

@WebServlet("/session/search/Drivers")
public class SearchDrivers extends HttpServlet {
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
		List<User> drivers = daoFactory.getUserDAO().listDrivers(UserOrderBy.NAME, true);
		
    	request.setAttribute("drivers", drivers);
		
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/search/Drivers.jsp")
			.forward(request, response);
    }

}
