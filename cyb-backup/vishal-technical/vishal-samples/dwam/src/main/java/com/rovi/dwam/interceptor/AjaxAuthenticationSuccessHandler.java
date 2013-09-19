/**
 * 
 */
package com.rovi.dwam.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import com.rovi.dwam.util.SessionKeys;

/**
 * @author vishalshu
 *
 */
public class AjaxAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	private RequestCache requestCache  = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        request.getSession().setAttribute(SessionKeys.LOGGED_IN_USER_SESSION.toString(), authentication.getPrincipal());
        responseWrapper.setContentType("APPLICATION/JSON");
        responseWrapper.getWriter().print(getDefaultTargetUrl());
        requestCache.removeRequest(request, response);
        clearAuthenticationAttributes(request);
        return;
	}
	
	
	
	public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
	
}
