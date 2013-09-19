package com.rovi.dwam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.rovi.dwam.exception.AuthorizationException;
import com.rovi.dwam.log.DWAMLogger;
import com.rovi.dwam.model.Customer;

/**
 * @author vishalshu
 * 
 */
@ImportResource("classpath:META-INF/spring/applicationContext-service.xml")
public class DwamAuthenticationProvider implements AuthenticationProvider
{
	private static DWAMLogger logger = DWAMLogger.getLogger(DwamAuthenticationProvider.class);

	@Autowired
	protected IUserService userService;

	//@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		Customer customer = null;
		try
		{
			customer = userService.authenticate(email, password);
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add( new SimpleGrantedAuthority("ROLE_USER") );

			Authentication auth = new UsernamePasswordAuthenticationToken(customer.getSessionId(), password, grantedAuths);
			return auth;

		}catch(AuthorizationException ae){
			logger.info("Authentication failed because the email or password is incorrect: {}", ae.getMessage());
			throw new BadCredentialsException(ae.getMessage());
		}
		catch (Exception e)
		{
			logger.info("User is not yet logged in", e.getMessage());
			throw new SessionAuthenticationException(e.getMessage());
		}
	}

	//@Override
	public boolean supports(Class<?> authentication)
	{
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
