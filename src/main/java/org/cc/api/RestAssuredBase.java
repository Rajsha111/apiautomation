package org.cc.api;

import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.common.HttpRequest;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredBase {

    public static ValidatableResponse triggerGetApi(HttpRequest httpRequest) {
        try {
            ValidatableResponse response = given()
                    .config(RestAssuredConfig.config()
                            .sslConfig(new SSLConfig().allowAllHostnames()))
                    .log().all().when().get(httpRequest.getUri()).then();
            response.log().all();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
