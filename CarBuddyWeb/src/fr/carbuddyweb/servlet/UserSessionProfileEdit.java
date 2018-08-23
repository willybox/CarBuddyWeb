package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.global.ConstantValues;
import fr.carbuddyweb.form.UserProfileEditForm;
import fr.carbuddyweb.global.ReadOnlyGlobal;

@WebServlet(
	urlPatterns = "/session/EditProfile",
	initParams = @WebInitParam(
		name = "imageStoragePath",
		value = "D:/Server/images/avatar/"
	)
)
@MultipartConfig(
	location = "D:/Server/images/avatar/",
	maxFileSize = 2 * 1024 * 1024, // 2 Mo
	maxRequestSize = 10 * 1024 * 1024, // 10 Mo
	fileSizeThreshold = 1024 * 1024 // 1 Mo
)
public class UserSessionProfileEdit extends HttpServlet {
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
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/EditProfile.jsp")
			.forward(request, response);
    }
	
	@Override
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException{
		User connectedUser = (User) request
			.getSession()
			.getAttribute(ReadOnlyGlobal.USER_SESSION);

		User user = new UserProfileEditForm(request, daoFactory)
			.updateUser(this
				.getServletConfig()
				.getInitParameter("imageStoragePath"));
		/** If user profile changed */
		if(!connectedUser.equals(user)) {
			request.getSession().setAttribute(ReadOnlyGlobal.USER_SESSION, user);
		}
		
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/session/EditProfile.jsp")
			.forward(request, response);
	}

}
