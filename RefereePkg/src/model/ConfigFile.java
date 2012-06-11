package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import model.Logging;

public class ConfigFile {
	private static String configFileFullName; // = new String( System.getenv(
												// "TASK_SERVER_CONFIG_DIR" ) +
												// File.separator +
												// "config.txt") ;
	private Properties properties;
	private Logging logg;
	private String configLogID = "Configuration";

	public ConfigFile() {
		properties = new Properties();
		logg = Logging.getInstance();
	}

	public boolean setConfigFile(File fileObj) {
		configFileFullName = new String(fileObj.getAbsolutePath());
		if (!fileObj.exists())
			return false;
		System.out.println("config file " + fileObj.getAbsolutePath()
				+ " exists.");
		return true;
	}

	public void loadProperties() throws Exception {
		try {
			FileInputStream in = new FileInputStream(
					ConfigFile.configFileFullName);
			properties.load(in);
			logg.LoggingFileAndCompetitionFile(configLogID, "Properties loaded from "
					+ configFileFullName, false);
			in.close();
		} catch (Exception e) {
			System.out.println("Exception in ConfigFile loadProperties: "
					+ e.getMessage());
			throw e;
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public long getConfigurationTime() {
		String str = properties.getProperty("configTime");
		long configTime = 0;
		Scanner scnr = new Scanner(str);
		if (scnr.hasNextLong()) {
			configTime = scnr.nextLong();
		} else
			logg.LoggingFileAndCompetitionFile(configLogID, "no configuration time", false);
		return configTime;
	}

	public String getServerIP() {
		String str = properties.getProperty("ServerIP");
		String refIP = null;
		Scanner scnr = new Scanner(str);
		if (scnr.hasNext()) {
			refIP = scnr.next();
			System.out.println(refIP);
		} else
			System.out.println("No ServerIP specified in the config file");
		return refIP;
	}
	
	public String getPortaddr() {
		String str = properties.getProperty("Port");
		String refPort = null;
		Scanner scnr = new Scanner(str);
		if (scnr.hasNext()) {
			refPort = scnr.next();
			System.out.println(refPort);
		} else
			System.out.println("No ServerIP specified in the config file");
		return refPort;
	}
	
	public long getRunTime() {
		String str = properties.getProperty("runTime");
		long runTime = 0;
		Scanner scnr = new Scanner(str);
		if (scnr.hasNextLong()) {
			runTime = scnr.nextLong();
		} else
			logg.LoggingFileAndCompetitionFile(configLogID, "no configuration time", true);
		return runTime;
	}
}
