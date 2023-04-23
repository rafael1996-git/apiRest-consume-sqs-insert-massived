package com.huellitas.serverless.utilities;

import com.huellazteca.core.utils.Environment;

public class Constants {
	
	public static final String FAILED_GET_DB_CONFIG = "Failed to get DB configuration.";
	public static final String CODIGO_FAILED_GET_DB_CONFIG = "-10";

	public static final String FAILED_GET_KEYS = "Failed to get Main and pass Keys.";
	public static final String FAILED_GET_KEYS_CONFIG = "-11";

	private static String initializeVariable(String value) {
		String result;
		try {
			result = Environment.get(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = null;
		}

		return result;
	}

}
