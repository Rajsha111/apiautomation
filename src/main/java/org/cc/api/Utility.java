package org.cc.api;

import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.ValidatableResponse;

public class Utility {

    public static boolean verifySchema(ValidatableResponse response, String schemaPath) {
        try {
            response.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
