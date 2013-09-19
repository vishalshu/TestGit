package com.rovi.dwam.log;

import org.slf4j.LoggerFactory;



/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 7, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class DWAMLogger
{
	public static DWAMLogger logger;
	private String name;
	private Class clazz;
	
	private DWAMLogger(Class clazz){
		this.clazz = clazz;
	}
	
	public static DWAMLogger getLogger(Class clazz){
		if(logger == null){
			return new DWAMLogger(clazz);
		}
		return logger;
	}
	
	public void info(String message){
		LoggerFactory.getLogger(clazz).info(message);
	}
	
	public void info(String format, Object... args){
		LoggerFactory.getLogger(clazz).info(format,args);
	}
	
	public void debug(String message){
		LoggerFactory.getLogger(clazz).debug(message);
	}
	
	public void debug(String message, Throwable t){
		LoggerFactory.getLogger(clazz).debug(message,t);
	}
	
	public void debug(String format, Object... args){
		LoggerFactory.getLogger(clazz).debug(format,args);
	}
	
	public void error(String message){
		LoggerFactory.getLogger(clazz).error(message);
	}
	
	public void error(String message, Throwable t){
		LoggerFactory.getLogger(clazz).error(message, t);
	}
	
	public void warn(String message){
		LoggerFactory.getLogger(clazz).warn(message);
	}
}
