package org.cc.test.badges;

import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.Utility;
import org.cc.api.badges.GetBadgeRecipient;
import org.cc.api.badges.GetBadgeTagsBaseScript;
import org.cc.api.badges.GetBadgesBaseScript;
import org.cc.api.common.CustomeException;
import org.cc.api.common.PropertyManager;
import org.cc.api.common.SchemaPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetBadgesApiTest {

    private int firstBadgeId;

    @BeforeClass(alwaysRun = true)
    public void getFirstBadgeId() throws CustomeException {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.getAllBadges(PropertyManager.getInstance().getAccessKey());
        Assert.assertEquals(validatableResponse.extract().statusCode(), 200, "Verify status code");
        firstBadgeId = GetBadgesBaseScript.getBadgeId(0, validatableResponse);
    }

    @Test(description = "Get badges using valid data")
    public void getBadgeUsingId() {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.getBadgesById(PropertyManager.getInstance().getAccessKey(), firstBadgeId);
        Assert.assertEquals(validatableResponse.extract().statusCode(), 200, "Verify status code");
        Assert.assertTrue(Utility.verifySchema(validatableResponse, SchemaPath.BADGE_ID_SCHEMA), "Verify schema");
    }

    @Test(description = "Get badge tags using valid data")
    public void getBadgeTags() {
        ValidatableResponse validatableResponse = GetBadgeTagsBaseScript.getBadgeTags(PropertyManager.getInstance().getAccessKey());
        Assert.assertEquals(validatableResponse.extract().statusCode(), 200, "Verify status code");
        Assert.assertTrue(Utility.verifySchema(validatableResponse, SchemaPath.BADGE_TAGS_SCHEMA), "Verify schema");
    }

    @Test(description = "Get badge receipent using valid data")
    public void getBadgeRecipient() {
        ValidatableResponse validatableResponse = GetBadgeRecipient.getBadgeRecipient(PropertyManager.getInstance().getAccessKey());
        Assert.assertEquals(validatableResponse.extract().statusCode(), 200, "Verify status code");
        Assert.assertTrue(Utility.verifySchema(validatableResponse, SchemaPath.BADGE_RECIPIENT_SCHEMA), "Verify schema");
    }
}
