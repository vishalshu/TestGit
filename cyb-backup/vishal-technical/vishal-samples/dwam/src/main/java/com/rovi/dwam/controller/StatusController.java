package com.rovi.dwam.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: ewallin
 * Created: Jul 11, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
@Controller
public class StatusController
{
	private static Logger log = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value = "/status.htm", method = RequestMethod.GET)
	public ModelAndView loadStatusPage(ModelMap model)
	{
		log.info("Loading status page...");
		
		try
		{
			InputStream inputStream = context.getResourceAsStream("/META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(inputStream);
			Attributes attr = manifest.getMainAttributes();
			String appVersion = attr.getValue("Implementation-Version");
			String buildVersion = attr.getValue("Implementation-Build");
			
			model.addAttribute("appVersion", appVersion);
			model.addAttribute("buildVersion", buildVersion);
		}
		catch (IOException e)
		{
			log.error("Failed to load properties from MANIFEST.MF", e);
		}
		
		return new ModelAndView("status", model);
	}
}
