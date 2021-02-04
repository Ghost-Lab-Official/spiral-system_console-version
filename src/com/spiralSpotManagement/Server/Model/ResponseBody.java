package com.spiralSpotManagement.Server.Model;

import java.util.List;

public class ResponseBody {
    private List<Object> response;

    public List<Object> getResponse() {
        return response;
    }

    public ResponseBody(List<Object> response) {
        this.response = response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }
}
