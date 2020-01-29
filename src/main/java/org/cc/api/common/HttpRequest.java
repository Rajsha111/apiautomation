package org.cc.api.common;

public class HttpRequest {
    private HttpMethod httpMethod;
    private final String uri;

    public HttpRequest(HttpMethod httpMethod, String uri) {
        this.httpMethod = httpMethod;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getMethod() {
        return httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }
}