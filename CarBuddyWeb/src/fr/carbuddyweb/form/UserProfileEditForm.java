package fr.carbuddyweb.form;

import static fr.carbuddy.enumeration.ValidationStatus.AVATAR_BAD_USE_REQUEST;
import static fr.carbuddy.enumeration.ValidationStatus.AVATAR_OVER_SIZE;
import static fr.carbuddy.enumeration.ValidationStatus.AVATAR_WRITE_FAILURE;
import static fr.carbuddy.enumeration.ValidationStatus.CONFIGURATION_LIMITATION;
import static fr.carbuddy.global.ConstantValues.AVATAR;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.service.UserService;
import fr.carbuddyweb.global.ReadOnlyGlobal;
import fr.carbuddyweb.util.ParamUtil;
import fr.carbuddyweb.util.PartUtil;
import util.library.add.on.file.AddOnFile;
import util.library.add.on.string.AddOnString;

public class UserProfileEditForm {

	private HttpServletRequest request;
	private DAOFactory daoFactory;

	public UserProfileEditForm(HttpServletRequest request, DAOFactory daoFactory) {
		this.request = request;
		this.daoFactory = daoFactory;
	}

	public User updateUser(String storagePath) {
		System.out.println(storagePath);
		User oldValues = (User) request
			.getSession()
			.getAttribute(ReadOnlyGlobal.USER_SESSION);
		UserService userService = new UserService(oldValues);
		User newValues = userService.createCopy();
		Set<ValidationStatus> errorsValidation = new HashSet<>();
		
		/** Values to update */
		
		/** -- Avatar -- */

		try {
			Part part = request.getPart(AVATAR);
	        String avatarName = PartUtil.getFileName(part);
			if(!AddOnString.isNullOrEmpty(avatarName)) {
				ValidationStatus avatarStatus = new UserService(newValues)
					.checkAvatar(part.getInputStream());
				if(avatarStatus != ValidationStatus.OK) {
					errorsValidation.add(avatarStatus);
				}

				StringBuilder pathDest = new StringBuilder()
					.append(storagePath)
					.append(oldValues.getId()).append(" - ").append(avatarName);

				try {
					String absPathDest = pathDest.toString();
					AddOnFile.copyFile(
						part.getInputStream(),
						absPathDest
					);
					newValues.setAvatar(absPathDest);
				} catch (Exception e) {
					errorsValidation.add(AVATAR_WRITE_FAILURE);
				}
			} /** Else, user did not chose new avatar, let it null */
		} catch (IllegalStateException e) {
            /**
             * Exception triggered if file size is superior to limits defined in
             * <multipart-config> in upload servlet declaration in web.xml
             */
			errorsValidation.add(AVATAR_OVER_SIZE);
        } catch (IOException e) {
            /**
             * Exception triggered if there is an error at storage destination
             * (missing directory, no access rights, etc.)
             */
			errorsValidation.add(CONFIGURATION_LIMITATION);
        } catch (ServletException e) {
            /**
             * Exception triggered if request is not multipart/form-data type.
             */
			errorsValidation.add(AVATAR_BAD_USE_REQUEST);
        }
		
		if(errorsValidation.isEmpty()) {
			newValues.setId(oldValues.getId());
			daoFactory.getUserDAO().updateUser(oldValues, newValues);
			return newValues;
		} else {
			ParamUtil.setErrorMapParameter(errorsValidation, request);
			return oldValues;
		}
	}

}
