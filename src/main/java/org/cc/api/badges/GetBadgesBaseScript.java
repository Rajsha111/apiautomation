package org.cc.api.badges;

import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.common.*;
import org.cc.api.readapi.Command;

import java.util.ArrayList;

public class GetBadgesBaseScript {

    private static final String baseUrl = PropertyManager.getInstance().getBaseUrl();

    public static ValidatableResponse getAllBadges(String accessKey) {
        ActionsImp actionsImp = ActionFactory.getActionFactory(HttpMethod.GET);
        Command command = new Command(baseUrl, ApiEndpoint.GET_BADGES, ImmutableMap.of(Parameters.ACCESS_KEY, accessKey));
        return actionsImp.execute(command);
    }

    public static ValidatableResponse getBadgesById(String accessKey, int badgeId) {
        ActionsImp actionsImp = ActionFactory.getActionFactory(HttpMethod.GET);
        Command command = new Command(baseUrl, ApiEndpoint.GET_BADGES_WITH_ID, ImmutableMap.of(Parameters.ACCESS_KEY,
                accessKey, Parameters.BADGE_ID, String.valueOf(badgeId)));
        return actionsImp.execute(command);
    }

    public static int getBadgeId(int badgeIndex, ValidatableResponse response) throws CustomeException {
        ArrayList badges = response.extract().body().path("items");
        if (badges != null && badges.size() > badgeIndex)
            return response.extract().body().path("items[" + badgeIndex + "].badge_id");
        else
            throw new CustomeException("Given badgeIndex '" + badgeIndex + "' is not correct or badges are not present");
    }

    public static int getTotalItems(ValidatableResponse response) throws CustomeException {
        ArrayList badges = response.extract().body().path("items");
        if (badges != null)
            return badges.size();
        throw new CustomeException("Given response is not valid");
    }

    public static ValidatableResponse triggerApiWithInvalidApiName(String apiName, String accessKey) {
        ActionsImp actionsImp = ActionFactory.getActionFactory(HttpMethod.GET);
        Command command = new Command(baseUrl, "/" + apiName + "?key={key}&site=stackoverflow", ImmutableMap.of(Parameters.ACCESS_KEY, accessKey));
        return actionsImp.execute(command);
    }
}
