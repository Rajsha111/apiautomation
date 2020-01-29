package org.cc.api.common;

import org.testng.Assert;

public class CustomeException extends Exception {

    public CustomeException(String message) {
        Assert.fail(message);
    }
}
