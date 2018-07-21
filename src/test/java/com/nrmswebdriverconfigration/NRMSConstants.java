package com.nrmswebdriverconfigration;

import com.nrms.DataReader.PropertiesReader;

public class NRMSConstants {
	public static final String SERVER_URL=PropertiesReader.getValue("SERVER_URL");
	public static final String PASSWORDRESETPAGE=PropertiesReader.getValue("PASSWORDRESETPAGE");;
	public static final String DB_USER=PropertiesReader.getValue("dbUsername");
	public static final String DB_PASSWORD=PropertiesReader.getValue("dbPassword");
}
  