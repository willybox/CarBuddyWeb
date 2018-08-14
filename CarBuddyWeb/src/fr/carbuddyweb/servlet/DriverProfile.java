package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.string.StatusUser;
import util.library.add.on.date.AddOnDate;

public class DriverProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException{
        String paramAuteur = request.getParameter( "auteur" );
		String[] arr = new String[] {"one", "two", "TREE", paramAuteur};

    	request.setAttribute( "monArr", arr );
    	
    	User d = new User();
    	d.setFirstname("Harold");
    	d.setName("FEVE");
    	d.setStatusUser(StatusUser.DRIVER);
    	d.setBirthday(new DateTime(1983, 10, 16, 0, 0).toDate());
    	request.setAttribute( "driver", d );
    	
    	request.setAttribute( "age", AddOnDate.getAge(d.getBirthday()) );
    	
		this
			.getServletContext()
			.getRequestDispatcher("/WEB-INF/DriverProfile.jsp")
			.forward(request, response);
    }

}
