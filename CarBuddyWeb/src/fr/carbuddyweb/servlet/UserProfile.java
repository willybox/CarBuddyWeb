package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.global.ConstantValues;
import util.library.add.on.date.AddOnDate;

@WebServlet("/session/User/*")
public class UserProfile extends HttpServlet {
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
		/** Get id on url */
		Long id = 1L;
		User u = daoFactory.getUserDAO().findById(id);
		
    	request.setAttribute( "user", u );
    	request.setAttribute( "age", AddOnDate.getAge(u.getBirthday()) );
    	
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/UserProfile.jsp")
			.forward(request, response);
    }

}
