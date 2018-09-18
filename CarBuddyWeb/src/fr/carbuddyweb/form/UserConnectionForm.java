package fr.carbuddyweb.form;

import static fr.carbuddy.global.ConstantValues.PASSWORD;
import static fr.carbuddy.global.ConstantValues.USERNAME;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.service.UserService;
import fr.carbuddyweb.global.ReadOnlyGlobal;
import fr.carbuddyweb.util.ParamUtil;

public class UserConnectionForm {

	private HttpServletRequest request;
	private DAOFactory daoFactory;

	public UserConnectionForm(HttpServletRequest request, DAOFactory daoFactory) {
		this.request = request;
		this.daoFactory = daoFactory;
	}

	public User getUser() {
		Set<ValidationStatus> errorMap = new HashSet<>();
		User user = daoFactory
			.getUserDAO()
			.findByUsername(request.getParameter(USERNAME));
		
		if(user == null) {
			errorMap.add(ValidationStatus.INVALID_USERNAME_OR_PASSWORD);
		} else {
			UserService userService = new UserService(user);
			
			//TODO Find a way to check number of tries
			int numberOfTries = 0;
			
			ValidationStatus error = userService
				.checkPasswordAuthentication(
					request.getParameter(PASSWORD),
					numberOfTries
				);
			if(error == ValidationStatus.OK) {
				/** Used in ConnectionFilter */
				request.setAttribute(ReadOnlyGlobal.USER_SESSION, user);
				
				return user;
			} else {
				errorMap.add(error);
			}
		}
		ParamUtil.setErrorMapParameter(errorMap, request);
		return null;
	}

}
