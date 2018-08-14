package fr.carbuddyweb.servlet;

import static fr.carbuddyweb.global.ReadOnlyGlobal.WEBSITE_ABSOLUTE_PATH;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) {
			session.setAttribute("a", "slt abruti :)");
			request.setAttribute("dummyHello", session.getAttribute("a"));
			
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/index.jsp")
				.forward(request, response);
		} else {
	        response.sendRedirect( WEBSITE_ABSOLUTE_PATH + "/Deconnection" );
		}
    }

}
