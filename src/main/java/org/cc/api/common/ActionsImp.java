package org.cc.api.common;

import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.readapi.Command;

public interface ActionsImp {

    public ValidatableResponse execute(Command command);

    public String getEndpoint(Command command);
}
