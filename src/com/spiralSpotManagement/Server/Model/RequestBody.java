package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class RequestBody implements Serializable {
    private String url; // /users
    private String index; //update
    private Object object; //{}

    public RequestBody(){}
    public RequestBody(String url, String index, Object object) {
        this.url = url;
        this.index = index;
        this.object = object;
    }
        /*
           /deleteUsers/1
           /updateUser/1

         */

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}