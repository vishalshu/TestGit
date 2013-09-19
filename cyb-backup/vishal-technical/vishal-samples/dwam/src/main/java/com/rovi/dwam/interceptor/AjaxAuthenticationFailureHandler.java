/**
 * 
 */
package com.rovi.dwam.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author vishalshu
 *
 */
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler{

	//@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
		responseWrapper.setContentType("APPLICATION/JSON");
        responseWrapper.getWriter().print(exception.getMessage());
        if(exception instanceof BadCredentialsException)
        {
        	responseWrapper.setStatus(401);
        }else{
        	responseWrapper.setStatus(403);
        }
        
       /* System.out.println("Auth failure");
        //response.sendRedirect("/home.htm");
*/      
        //throw new RuntimeException("Failure of login");
        		
	}

}
