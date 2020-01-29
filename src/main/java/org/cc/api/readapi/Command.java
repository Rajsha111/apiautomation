package org.cc.api.readapi;

import java.util.Map;

public class Command {
    private final String url;
    private final String endPoint;
    private Map<String, String>  parameter;
    private Map<String, String>  header;

    public Command(String url, String endPoint, Map<String, String> parameter){
        this.url = url;
        this.endPoint = endPoint;
        this.parameter = parameter;
    }

    public Command(String url, String endPoint){
        this(url, endPoint, null);
    }

    public Command(String url, String endPoint, Map<String, String> parameter, Map<String, String> header){
        this.url = url;
        this.endPoint = endPoint;
        this.parameter = parameter;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public String getUrl() {
        return url;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }
}
