package com.rodale.util;

import java.util.ResourceBundle;

import com.marklogic.client.DatabaseClientFactory.Authentication;

public class Config {

private static ResourceBundle rb = ResourceBundle.getBundle("Config");
	
	
	public static String host = rb.getString("rodale.db.host");
	
	public static int port = Integer.parseInt(rb.getString("rodale.db.port"));
	
	public static String user = rb.getString("rodale.db.writer_user");
	
	public static String password = rb.getString("rodale.db.writer_password");
	
	public static String admin_user = rb.getString("rodale.db.admin_user");
	
	public static String admin_password = rb.getString("rodale.db.admin_password");
	
	public static Authentication authType = Authentication.valueOf(
			rb.getString("rodale.db.authentication_type").toUpperCase()
				);
}
