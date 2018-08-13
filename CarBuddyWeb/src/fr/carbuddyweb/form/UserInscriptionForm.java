package fr.carbuddyweb.form;

import static fr.carbuddyweb.global.ReadOnlyGlobal.BIRTHDAY;
import static fr.carbuddyweb.global.ReadOnlyGlobal.CITY;
import static fr.carbuddyweb.global.ReadOnlyGlobal.CONFIRM_PW;
import static fr.carbuddyweb.global.ReadOnlyGlobal.COUNTRY;
import static fr.carbuddyweb.global.ReadOnlyGlobal.E_MAIL;
import static fr.carbuddyweb.global.ReadOnlyGlobal.FIRSTNAME;
import static fr.carbuddyweb.global.ReadOnlyGlobal.NAME;
import static fr.carbuddyweb.global.ReadOnlyGlobal.PASSWORD;
import static fr.carbuddyweb.global.ReadOnlyGlobal.PHONE;
import static fr.carbuddyweb.global.ReadOnlyGlobal.POSTAL;
import static fr.carbuddyweb.global.ReadOnlyGlobal.STREET;
import static fr.carbuddyweb.global.ReadOnlyGlobal.USER_NAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.service.CreationService;
import util.library.add.on.string.AddOnString;

public class UserInscriptionForm {

	private HttpServletRequest request;

	public UserInscriptionForm(HttpServletRequest request) {
		this.request = request;
	}

	public User newUser() {
		HashMap<String, String> errorsMap = new HashMap<>();
		CreationService creationService = new CreationService();
		
		String birthday = request.getParameter(BIRTHDAY);
		Date birthDate = null;
		if(!AddOnString.isNullOrEmpty(birthday)) {
			 SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			 try {
				birthDate = parser.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Address address = creationService.createAddress(
			request.getParameter(CITY),
			request.getParameter(COUNTRY),
			request.getParameter(POSTAL),
			request.getParameter(STREET)
		);
		Set<ValidationStatus> errorsValidation = creationService.getErrorsValidation();
		User newUser = creationService.createUser(
			request.getParameter(USER_NAME),
			request.getParameter(PASSWORD),
			request.getParameter(CONFIRM_PW),
			request.getParameter(E_MAIL),
			request.getParameter(NAME),
			request.getParameter(FIRSTNAME),
			request.getParameter(PHONE),
			birthDate,
			address
		);
		errorsValidation.addAll(creationService.getErrorsValidation());

		if(errorsValidation.isEmpty()) {
			request.setAttribute("newUser", newUser);
		} else {
			for(ValidationStatus error : errorsValidation) {
				String key = error.name().split("_")[0];
				String oldValue = errorsMap.get(key);
				String newValue = error.toString();
				
				if(oldValue == null) {
					errorsMap.put(key, newValue);
				} else {
					errorsMap.put(key, oldValue + ";" + newValue);
				}
			}
			request.setAttribute("errorsMap", errorsMap);
		}
		
		return null;
	}
}
