package com.rovi.dwam.util;

import java.util.Locale;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * @author DivX Content Services Engineering: ewallin
 * Created: Mar 23, 2010
 * Copyright DivX Networks 2010
 *
 * Developer Comment(s):
 * Use com.divx.ovs.util.LocaleUtility instead 
 */
@Deprecated
public class LocaleUtil
{
	/**
	 * Returns the given locale formatted as a lower case, hyphen separated value, where 2 characters each are used for the language and country codes.
	 * Example: An input locale of en_US will be returned as en-us
	 * @param locale
	 * @return
	 */
	public static String getLocaleCodeInLowercase(Locale locale)
	{
		String lc = "";
		String localeCode = locale.toString();
		
		if (localeCode.equals("zh_CN"))
		{
			lc = "zh-hans";
		}
		else if (localeCode.equals("zh_TW"))
		{
			lc = "zh-hant";
		}
		else
		{
			lc = localeCode.toLowerCase().replace("_", "-");
		}
		
		return lc;
	}
}
