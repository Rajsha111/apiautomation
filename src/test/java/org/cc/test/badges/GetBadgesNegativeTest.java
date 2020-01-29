package org.cc.test.badges;

import com.jayway.restassured.response.ValidatableResponse;
import org.cc.api.badges.GetBadgeRecipient;
import org.cc.api.badges.GetBadgeTagsBaseScript;
import org.cc.api.badges.GetBadgesBaseScript;
import org.cc.api.common.CustomeException;
import org.cc.api.common.Error;
import org.cc.api.common.HttpStatusCode;
import org.cc.api.common.PropertyManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetBadgesNegativeTest {

    private int firstBadgeId;

    @BeforeClass(alwaysRun = true)
    public void getFirstBadgeId() throws CustomeException {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.getAllBadges(PropertyManager.getInstance().getAccessKey());
        Assert.assertEquals(validatableResponse.extract().statusCode(), 200, "Verify status code");
        firstBadgeId = GetBadgesBaseScript.getBadgeId(0, validatableResponse);
    }

    @Test(description = "Get badge using invalid access Key And BadgeId")
    public void getBadgeUsingInvalidAccessKeyAndBadgeId() {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.getBadgesById("", -1);
        Assert.assertEquals(validatableResponse.extract().statusCode(), HttpStatusCode.HTTP_400, "Verify status code");
        Assert.assertEquals(validatableResponse.extract().body().path("error_id"), Error.ERROR_ID_400, "Verify error_id fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_message"), Error.ERROR_MESSAGE_400, "Verify error_message fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_name"), Error.ERROR_NAME_400, "Verify error_name fail");
    }

    @Test(description = "Get badge with id and invalid access key", dataProvider = "invalidAccessKey")
    public void getBadgeUsingIdWithInvalidAccessKey(String accessKey) {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.getBadgesById(accessKey, firstBadgeId);
        Assert.assertEquals(validatableResponse.extract().statusCode(), HttpStatusCode.HTTP_400, "Verify status code");
        Assert.assertEquals(validatableResponse.extract().body().path("error_id"), Error.ERROR_ID_400, "Verify error_id fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_message"), Error.ERROR_MESSAGE_400, "Verify error_message fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_name"), Error.ERROR_NAME_400, "Verify error_name fail");
    }

    @DataProvider(name = "invalidAccessKey")
    public Object[][] invalidAccess() {
        return new Object[][]{
                {"iK7iijIWf4K5dhBtFJUnig(("},
                {"-1"},
                {""}
        };
    }

    @Test(description = "Get badge with invalid badge id", dataProvider = "invalidBadgeId")
    public void getBadgeWithInvalidBadgeId(int badgeId) throws CustomeException {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.getBadgesById(PropertyManager.getInstance().getAccessKey(), badgeId);
        Assert.assertEquals(validatableResponse.extract().statusCode(), HttpStatusCode.HTTP_200, "Verify status code");
        Assert.assertEquals(GetBadgesBaseScript.getTotalItems(validatableResponse), 0, "Verify items are not present");
    }

    @DataProvider(name = "invalidBadgeId")
    public Object[][] invalidBadgeIdData() {
        return new Object[][]{
                {0},
                {-1}
        };
    }

    @Test(description = "Get badge tags using invalid access key")
    public void getBadgeTagsWithInvalidAccessKey() {
        ValidatableResponse validatableResponse = GetBadgeTagsBaseScript.getBadgeTags("iK7iijIWf4K5dhBtFJUnig((");
        Assert.assertEquals(validatableResponse.extract().statusCode(), HttpStatusCode.HTTP_400, "Verify status code");
        Assert.assertEquals(validatableResponse.extract().body().path("error_id"), Error.ERROR_ID_400, "Verify error_id fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_message"), Error.ERROR_MESSAGE_400, "Verify error_message fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_name"), Error.ERROR_NAME_400, "Verify error_name fail");
    }

    @Test(description = "Get badge recipient using invalid access key")
    public void getBadgeRecipientWithInvalidAccessKey() {
        ValidatableResponse validatableResponse = GetBadgeRecipient.getBadgeRecipient("iK7iijIWf4K5dhBtFJUnig((");
        Assert.assertEquals(validatableResponse.extract().statusCode(), HttpStatusCode.HTTP_400, "Verify status code");
        Assert.assertEquals(validatableResponse.extract().body().path("error_id"), Error.ERROR_ID_400, "Verify error_id fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_message"), Error.ERROR_MESSAGE_400, "Verify error_message fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_name"), Error.ERROR_NAME_400, "Verify error_name fail");
    }

    @Test(description = "Get badges using invalid url")
    public void getBadgesWithInvalidUrl() {
        ValidatableResponse validatableResponse = GetBadgesBaseScript.triggerApiWithInvalidApiName("bad", PropertyManager.getInstance().getAccessKey());
        Assert.assertEquals(validatableResponse.extract().statusCode(), HttpStatusCode.HTTP_400, "Verify status code");
        Assert.assertEquals(validatableResponse.extract().body().path("error_id"), Error.ERROR_ID_404, "Verify error_id fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_message"), Error.ERROR_MESSAGE_404, "Verify error_message fail");
        Assert.assertEquals(validatableResponse.extract().body().path("error_name"), Error.ERROR_NAME_404, "Verify error_name fail");
    }

}
