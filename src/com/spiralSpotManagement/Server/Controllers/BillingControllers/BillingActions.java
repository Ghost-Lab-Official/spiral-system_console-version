package com.spiralSpotManagement.Server.Controllers.BillingControllers;

import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.BillingModel;
import com.spiralSpotManagement.Server.Model.ResponseStatus;

import java.sql.*;

public class BillingActions {

    /**
     * billing class
     * @author Divine
     * @param billing_id
     * @description delete the bill if it exists in database
     */

    public   boolean CheckBillingId(int billing_id){
        String query = "SELECT billing_id FROM `billing` WHERE billing_id =?";
        boolean checkResult = false;
        try {
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            PreparedStatement checkStatment = connection.prepareStatement(query);
            checkStatment.setInt(1,billing_id);

            ResultSet rs = checkStatment.executeQuery();
            if (rs.next()){
                connection.close();
                checkResult = true;
            }
        }catch (SQLException e){
            System.out.println("Error Message: "+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkResult;
    }

    public ResponseStatus DeleteBilling(BillingModel billing){
        try{
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            if(!CheckBillingId(billing.getBilling_id())){
                return new ResponseStatus(400,"BAD REQUEST","Oops,Entered billing doesn't exists");
            }
            String status = "inactive";
            String sql = "UPDATE billing SET billing_status = ? WHERE billing_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,status);
            stmt.setInt(2,billing.getBilling_id());
            int updated = stmt.executeUpdate();
            if(updated<1){
                connection.close();
                return  new ResponseStatus(500,"SERVER ERROR","Oops unable to delete, pLease try again!!");
            }
            connection.close();
            return  new ResponseStatus(200,"DELETED","Successfully deleted");

        }catch (Exception e){
            return new ResponseStatus(500,"SERVER ERROR",e.getMessage());
        }

    }
}
