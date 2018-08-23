package fr.carbuddyweb.util;

import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import fr.carbuddy.enumeration.ValidationStatus;

public class ParamUtil {

	public static void setErrorMapParameter(
		Set<ValidationStatus> errorsValidation,
		HttpServletRequest request
	) {
		HashMap<String, String> errorsMap = new HashMap<>();
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
}
