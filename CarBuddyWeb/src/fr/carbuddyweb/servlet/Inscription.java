package fr.carbuddyweb.servlet;

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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.enumeration.string.StatusUser;
import fr.carbuddy.service.PasswordService;
import fr.carbuddy.validation.UserValidation;
import util.library.add.on.string.AddOnString;

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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

		HashMap<String, String> errorsMap = new HashMap<>();
		
		String username = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		String confirmPW = request.getParameter(CONFIRM_PW);
		
		ValidationStatus passwordsMatchStatus = new PasswordService(password)
			.isNewPasswordSameAsConfirmation(confirmPW);
		if(passwordsMatchStatus != ValidationStatus.OK) {
			errorsMap.put("PASSWORD", passwordsMatchStatus.toString());
		}

		String email = request.getParameter(E_MAIL);
		String name = request.getParameter(NAME);
		String firstname = request.getParameter(FIRSTNAME);
		String phone = request.getParameter(PHONE);
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
		
		String city = request.getParameter(CITY);
		String country = request.getParameter(COUNTRY);
		String postal = request.getParameter(POSTAL);
		String street = request.getParameter(STREET);

		Address adress = new Address();
		adress.setCity(city);
		adress.setCountry(country);
		adress.setPostal(postal);
		adress.setStreet(street);
		
		User newUser = new User();
		newUser.setAddress(adress);
		newUser.setBirthday(birthDate);
		newUser.setEmail(email);
		newUser.setFirstname(firstname);
		newUser.setName(name);
		newUser.setPhone(phone);
		newUser.setPassword(password);
		newUser.setStatusUser(StatusUser.BUDDY);
		newUser.setUserName(username);
		
		Set<ValidationStatus> errorsValidation =
			new UserValidation(newUser).checkValidity();
		if(errorsValidation.isEmpty()) {
			request.setAttribute("newUser", newUser);
			
			//TODO Persistence
			
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/ValidationInscription.jsp")
				.forward(request, response);
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
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/Inscription.jsp")
				.forward(request, response);
		}
    }

}
