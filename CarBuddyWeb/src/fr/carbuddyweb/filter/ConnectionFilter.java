package fr.carbuddyweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.carbuddyweb.global.ReadOnlyGlobal;

public class ConnectionFilter implements Filter {

	@Override
    public void init(FilterConfig config) throws ServletException {

    }

	@Override
	public void doFilter(
		ServletRequest req,
		ServletResponse res,
		FilterChain chain
	) throws IOException, ServletException {
		
        /** Cast */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /** Never filter include folder (css/js...) */
        String path = request
        	.getRequestURI()
        	.substring(request.getContextPath().length());

        if(path.startsWith("/include")) {
            chain.doFilter( request, response );
            return;
        }
        
		HttpSession session = request.getSession();
		
		if(session.getAttribute(ReadOnlyGlobal.USER_SESSION) != null) {
			session.setAttribute("a", "slt abruti :)");
			request.setAttribute("dummyHello", session.getAttribute("a"));

            /** Filter */
            chain.doFilter( request, response );
		} else {
	        response.sendRedirect( request.getContextPath() + "/Deconnection" );
		}
	}

	@Override
	public void destroy() {
		
	}

}
