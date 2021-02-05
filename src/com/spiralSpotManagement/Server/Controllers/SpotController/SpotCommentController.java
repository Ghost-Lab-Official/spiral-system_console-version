package com.spiralSpotManagement.Server.Controllers.SpotController;

import com.spiralSpotManagement.Server.Controllers.SpotCategoryControllers.SpotCategoryActions;
import com.spiralSpotManagement.Server.Model.*;

import java.util.ArrayList;
import java.util.List;

public class SpotCommentController {
    public List<Object> mainMethod(RequestBody requestBody)throws Exception{
        String action = requestBody.getAction();
        List<Object> usersObject = new ArrayList<>();
        switch (action){
            case "register":
                ResponseStatus registeredStatus = new SpotCommentActions().insertComment((Comment) requestBody.getObject());
                usersObject.add((Object) registeredStatus);
                return  usersObject;

            case "update":
                ResponseStatus updatedStatus = new SpotCommentActions().updateComment((Comment) requestBody.getObject());
                usersObject.add((Object) updatedStatus);
                return  usersObject;

            case "reply-comment":
                ResponseStatus replyStatus = new SpotCommentActions().makeCommentReply((Comment) requestBody.getObject());
                usersObject.add((Object) replyStatus);
                return  usersObject;

            case "update-status":
                ResponseStatus updatedCommentStatus = new SpotCommentActions().updateCommentStatus((Comment) requestBody.getObject());
                usersObject.add((Object) updatedCommentStatus);

                return  usersObject;

            default:
                return usersObject;
        }
    }
}
