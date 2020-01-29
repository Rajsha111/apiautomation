package org.cc.api.common;

public class ApiEndpoint {
    public static final String GET_BADGES = "/badges?key={key}&site=stackoverflow";
    public static final String GET_BADGES_WITH_ID = "/badges/{id}?key={key}&site=stackoverflow";
    public static final String GET_TAGS = "/badges/tags?key={key}&site=stackoverflow";
    public static final String GET_RECIPIENTS = "/badges/recipients?key={key}&site=stackoverflow";
}
