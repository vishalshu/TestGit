package com.rovi.dwam.interceptor;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rovi.dwam.util.DwamUserContext;

/**
 * Servlet Filter implementation class NoCacheFilter
 */
public class LocaleFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public LocaleFilter()
	{
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		// pass the request along the filter chain
		HttpServletResponse res = (HttpServletResponse) response;

		/* res.setHeader("Expires", "Thu, 01 Jan 1970 00:00:00 GMT"); */
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.addHeader("Cache-Control", "must-revalidate");
		res.addHeader("Cache-Control", "private");
		res.addHeader("Cache-Control", "max-stale=0");
		res.addHeader("Cache-Control", "pre-check=0");
		res.addHeader("Cache-Control", "post-check=0");
		res.setHeader("Vary", "*");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "Thu, 01 Jan 1970 00:00:00 GMT");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		DwamUserContext.setCountry(httpRequest.getLocale().getCountry());

		Cookie[] cookies = httpRequest.getCookies();
		if ((httpRequest != null) && (cookies != null))
		{
			for (Cookie cookie : cookies)
			{
				if ("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE".equals(cookie.getName()))
				{
					DwamUserContext.setLanguage(cookie.getValue());
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
