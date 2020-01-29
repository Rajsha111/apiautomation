package org.cc.api.common;

import org.cc.api.readapi.GetAction;

public class ActionFactory {

    public static ActionsImp getActionFactory(HttpMethod httpMethod){
        switch (httpMethod){
            case GET:
                return new GetAction();

                default:
                    break;
        }
        return null;
    }
}
