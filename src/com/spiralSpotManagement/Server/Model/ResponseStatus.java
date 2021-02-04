package com.spiralSpotManagement.Server.Model;

import java.io.Serializable;

public class ResponseStatus implements Serializable {
    private Integer status;
    private String message;
    private String actionToDo;

    public ResponseStatus(Integer status, String message, String actionToDo) {
        this.status = status;
        this.message = message;
        this.actionToDo = actionToDo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActionToDo() {
        return actionToDo;
    }

    public void setActionToDo(String actionToDo) {
        this.actionToDo = actionToDo;
    }
}
