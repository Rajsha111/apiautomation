package org.cc.api.badges;

import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.common.*;
import org.cc.api.readapi.Command;

public class GetBadgeRecipient {

    private static final String baseUrl = PropertyManager.getInstance().getBaseUrl();

    public static ValidatableResponse getBadgeRecipient(String accessKey) {
        ActionsImp actionsImp = ActionFactory.getActionFactory(HttpMethod.GET);
        Command command = new Command(baseUrl, ApiEndpoint.GET_RECIPIENTS, ImmutableMap.of(Parameters.ACCESS_KEY, accessKey));
        return actionsImp.execute(command);
    }
}
