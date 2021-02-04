package com.spiralSpotManagement.Server.Controllers.SpotControllers;

import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.SpotCategory;

/*
 @author: ntwari egide
 @descriptions: all spot category actions goes here
 */
public class SpotCategoryActions {
    public ResponseStatus addNewSpotCategory(SpotCategory spotCategoryToAdd){
        return new ResponseStatus(200,"SPOT ADDED","Insertion failed, try or contact System Administrator");
    }
}
