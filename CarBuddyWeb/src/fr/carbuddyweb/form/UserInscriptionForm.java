package fr.carbuddyweb.form;

import static fr.carbuddy.global.ConstantValues.BIRTHDAY;
import static fr.carbuddy.global.ConstantValues.CITY;
import static fr.carbuddy.global.ConstantValues.CONFIRM_PW;
import static fr.carbuddy.global.ConstantValues.COUNTRY;
import static fr.carbuddy.global.ConstantValues.E_MAIL;
import static fr.carbuddy.global.ConstantValues.FIRSTNAME;
import static fr.carbuddy.global.ConstantValues.NAME;
import static fr.carbuddy.global.ConstantValues.PASSWORD;
import static fr.carbuddy.global.ConstantValues.PHONE;
import static fr.carbuddy.global.ConstantValues.POSTAL;
import static fr.carbuddy.global.ConstantValues.*;
import static fr.carbuddy.global.ConstantValues.STREET;
import static fr.carbuddy.global.ConstantValues.USERNAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.service.CreationService;
import fr.carbuddyweb.util.ParamUtil;
import util.library.add.on.string.AddOnString;

public class UserInscriptionForm {

	private HttpServletRequest request;
	private DAOFactory daoFactory;

	public UserInscriptionForm(HttpServletRequest request, DAOFactory daoFactory) {
		this.request = request;
		this.daoFactory = daoFactory;
	}

	public User newUser() {
		CreationService creationService = new CreationService(daoFactory);
		
		String birthday = request.getParameter(BIRTHDAY);
		Date birthDate = null;
		if(!AddOnString.isNullOrEmpty(birthday)) {
			 SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			 try {
				birthDate = parser.parse(birthday);
			} catch (ParseException e) {
				throw new DAORuntimeException("Date bad format, expected yyyy-MM-dd (like 2000/12/31) but was " + birthday);
			}
		}
		
		Set<ValidationStatus> errorsValidation = new HashSet<>();
		Address address = null;
		
		/** Try to avoid doubloon?
		 * Should not do it because if user change address, he
		 * changes address of shared id address...
		 */
		try {
			address = creationService.createAddress(
				request.getParameter(CITY),
				request.getParameter(COUNTRY),
				request.getParameter(POSTAL),
				request.getParameter(STREET)
			);
		} catch(NotValidException e) {
			errorsValidation.addAll(e.getErrorsValidation());
		}
		User newUser = null;
		try {
			newUser = creationService.createUser(
				request.getParameter(USERNAME),
				request.getParameter(PASSWORD),
				request.getParameter(CONFIRM_PW),
				request.getParameter(GENDER),
				request.getParameter(E_MAIL),
				request.getParameter(NAME),
				request.getParameter(FIRSTNAME),
				request.getParameter(PHONE),
				birthDate,
				address
			);
		} catch(NotValidException e) {
			errorsValidation.addAll(e.getErrorsValidation());
		}

		if(errorsValidation.isEmpty()) {
			request.setAttribute("newUser", newUser);
			
			return newUser;
		} else {
			ParamUtil.setErrorMapParameter(errorsValidation, request);
		}
		
		return null;
	}
}
