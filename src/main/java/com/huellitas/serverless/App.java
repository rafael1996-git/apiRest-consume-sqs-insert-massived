package com.huellitas.serverless;

import com.huellazteca.core.BaseApp;
import com.huellazteca.core.domain.common.SQSRequest;
import com.huellazteca.core.utils.GsonParser;
import com.huellazteca.core.utils.LogHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.huellitas.serverless.models.domain.Pet;
import com.huellitas.serverless.services.Repository;

public class App extends BaseApp {
	Repository service = new Repository();
    @Override
    protected void init() {
        this.service = new Repository();
        this.REQUEST_FROM_SQS = true;
        this.ENCRYPTED_REQUEST = false;
        this.ENCRYPTED_RESPONSE = false;
    }

    @Override
    protected Object preHandler(Object request) {
        return request;
    }

    @Override
    protected Object handleRequest(Object request) {
        System.out.println("REQUEST:: " + request);
        SQSRequest sqsRequest = (SQSRequest) request;
        sqsRequest.getRecords().forEach(sqsMessage -> {
            try {
                Pet Data= GsonParser.deserialize(sqsMessage.getBody(), Pet.class);
                this.service.SetLoad( Data);
            }catch (Exception e){
                e.printStackTrace();
                LogHandler.manageInternalException("App.handleRequest: ",e);
            }
        });
        return "succes";
    }

    @Override
    protected Object postHandler(Object response) {
        return response;
    }

    @Override
    protected APIGatewayProxyResponseEvent handleRequestProxy(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        System.out.println(apiGatewayProxyRequestEvent.toString());
        return null;
    }
 

}

