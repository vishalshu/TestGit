package com.rovi.dwam.util;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Jul 24,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class DwamUserContext
{

	private static ThreadLocal<Locale> locale = new ThreadLocal<Locale>()
	{
		@Override
		protected Locale initialValue()
		{
			Locale locale = new Locale("en", "US");
			return locale;
		}
	};
	
	public static void setLanguage(String lang)
	{
		String country = locale.get().getCountry();
		
		if ( lang.contains("_") ) 
		{
			String[] langCode = parse( lang, "_" );
			Locale userLocale = new Locale(langCode[0], langCode[1]);
			locale.set(userLocale);
		}
		else
		{
		Locale userLocale = new Locale(lang, "");
		locale.set(userLocale);
		}
	}

	public static void setCountry(String country)
	{
		String lang = locale.get().getLanguage();
		Locale userLocale = new Locale(lang, country);
		locale.set(userLocale);
	}

	public static Locale getLocale()
	{
		return locale.get();
	}

	public static String getLanguage()
	{
		return locale.get().getLanguage();
	}

	public static String getCountry()
	{
		return locale.get().getCountry();
	}
	public static String[] parse( String strMsg, String token )
	{
		StringTokenizer tok = new StringTokenizer( strMsg, token, true );
		int maxToken;
		maxToken = tok.countTokens();
		String[] fields = new String[maxToken];
		String[] x = null;
		try
		{
			int maxFields = 0;
			for ( int i = 0; i < maxToken; i++ )
			{
				fields[i] = tok.nextToken();
				if ( fields[i].equals( token ) )
				{
					maxFields++;
				}
			}

			int field = 0;
			x = new String[maxFields + 1];
			char instr;
			for ( int i = 0; i < fields.length; i++ )
			{
				if ( !fields[i].equals( token ) )
				{
					x[field] = fields[i];
					field++;
				}
				else
					if ( i < maxToken - 1 && fields[i + 1].equals( token ) )
					{
						x[field] = "";
						field++;
					}
			}
		}
		catch ( NoSuchElementException e )
		{
			// stop parsing and return...
		}
		return x;
	}

}
