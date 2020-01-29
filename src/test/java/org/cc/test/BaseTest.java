package org.cc.test;

import org.cc.api.common.PropertyManager;

public class BaseTest {

    public final String accessKey = PropertyManager.getInstance().getAccessKey();
    public final String baseUrl = PropertyManager.getInstance().getBaseUrl();
}
