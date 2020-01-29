package org.cc.api.readapi;

import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.common.HttpMethod;
import org.cc.api.common.HttpRequest;
import org.cc.api.RestAssuredBase;
import org.cc.api.common.ActionsImp;

import java.util.Map;

public class GetAction extends RestAssuredBase implements ActionsImp {

    public static final String BRACKET = "{%s}";

    public ValidatableResponse execute(Command command) {
        String baseUrl = command.getUrl() + getEndpoint(command);
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, baseUrl);
        return triggerGetApi(httpRequest);
    }

    public String getEndpoint(Command command) {
        Map<String, String> parameters = command.getParameter();
        String endPoint = command.getEndPoint();
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                endPoint = endPoint.replace(String.format(BRACKET, entry.getKey()), entry.getValue());
            }
        }
        return endPoint;
    }
}
