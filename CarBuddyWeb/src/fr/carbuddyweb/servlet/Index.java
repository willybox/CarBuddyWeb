package fr.carbuddyweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.carbuddyweb.global.ReadOnlyGlobal;

@WebServlet("/session/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(ReadOnlyGlobal.USER_SESSION) != null) {
			session.setAttribute("a", "slt abruti :)");
			request.setAttribute("dummyHello", session.getAttribute("a"));
			
			this
				.getServletContext()
				.getRequestDispatcher("/WEB-INF/session/index.jsp")
				.forward(request, response);
		} else {
	        response.sendRedirect( request.getContextPath() + "/Deconnection" );
		}
    }

}
