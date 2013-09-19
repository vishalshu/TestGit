package com.rovi.dwam.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class DwamObjectMapper extends ObjectMapper
{
	public DwamObjectMapper()
	{
		super();
		configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}
}
