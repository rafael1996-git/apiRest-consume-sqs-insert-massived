package com.huellitas.serverless.utilities;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;

public class SecretManagerAWSUtils {
    private static final String SECRET_MANAGER_REFERENCE = "/aws/reference/secretsmanager/";
    private static AWSSimpleSystemsManagement client = null;

    public SecretManagerAWSUtils() {
    }

    public static String getParameter(String parameterName) throws Exception {
        if (client == null) {
            client = AWSSimpleSystemsManagementClientBuilder.standard().build();
        }

        String name = StringBuilderConcat.concat(new Object[]{"/aws/reference/secretsmanager/", parameterName});
        GetParameterRequest parameterRequest = (new GetParameterRequest()).withName(name).withWithDecryption(true);
        GetParameterResult getParameterResult = client.getParameter(parameterRequest);
        if (getParameterResult != null && getParameterResult.getParameter() != null && getParameterResult.getParameter().getValue() != null && !getParameterResult.getParameter().getValue().isEmpty()) {
            return getParameterResult.getParameter().getValue();
        } else {
            throw new Exception("El parametro no existe o no tiene un valor asignado");
        }
    }
}
