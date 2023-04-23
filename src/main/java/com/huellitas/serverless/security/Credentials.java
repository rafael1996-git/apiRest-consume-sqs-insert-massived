package com.huellitas.serverless.security;

import com.huellazteca.core.utils.Environment;
import com.huellazteca.core.utils.GsonParser;
import com.huellitas.serverless.utilities.SecretManagerAWSUtils;

public class Credentials {
	public static final DBConfig DB_CONFIG = initCredentials("OraCredKey", DBConfig.class);	
	private static <TType> TType initCredentials(String keyName, Class<TType> classType)  {
		try {
			String encryption = SecretManagerAWSUtils.getParameter(Environment.get(keyName));
			return GsonParser.deserialize(encryption, classType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
