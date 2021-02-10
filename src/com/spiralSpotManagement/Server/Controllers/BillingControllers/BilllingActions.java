package com.spiralSpotManagement.Server.Controllers.BillingControllers;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.BillingModel;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BilllingActions {

    /**
     *  Register a new billing plan.
     *  The function will receive an object of a plan directly insert into table of billing
     * @author Gervais Ishimwe
     * @param 'BillingInfo Object'
     * @return an Object of response
     */

    public ResponseStatus registerBillPlan(BillingModel billPlan) throws Exception{
        String query = "INSERT INTO billing(billing_name,billing_price,billing_period)VALUES(?,?,?)";
        try{
            Connection connection = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,billPlan.getBilling_name());
            stmt.setInt(2,billPlan.getPrice());
            stmt.setInt(3,billPlan.getBilling_period());
            int inserted_rec = stmt.executeUpdate();
            if(inserted_rec == 1){
                return new ResponseStatus(200,"CREATED","Billing plan registered");
            }
            if(connection != null){
                return new ResponseStatus(500,"SERVER ERROR","Insertion failed, try or contact System Administrator");
            }


        }catch(Exception e){
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
        return new ResponseStatus(200,"CREATED","Billing plan registered");
    }




    public ResponseStatus updateBillingPlan(BillingModel billPlan) throws Exception{
        String query = "UPDATE billing SET billing_name = ?, billing_price = ?, billing_period=?, billing_status = ? WHERE billing_id = ? ";
        try{
            Connection connection = new CloudStorageConnectionHandler().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,billPlan.getBilling_name());
            stmt.setInt(2,billPlan.getPrice());
            stmt.setInt(3,billPlan.getBilling_period());
            stmt.setString(4,billPlan.getBilling_status());
            stmt.setInt(5,billPlan.getBilling_id());

            int inserted_rec = stmt.executeUpdate();
            if(inserted_rec == 1){
                return new ResponseStatus(200,"UPDATED","Billing plan updated");
            }
            if(connection != null){
                return new ResponseStatus(500,"SERVER ERROR","Updating failed, try or contact System Administrator");
            }


        }catch(Exception e){
            return new ResponseStatus(300,"EXCEPTIONAL ERROR",e.getMessage());
        }
        return new ResponseStatus(200,"UPDATED","Billing plan updated");
    }
}
