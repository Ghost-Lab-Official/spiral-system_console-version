package com.spiralSpotManagement.Server.Controllers.BillingControllers;

import com.spiralSpotManagement.Client.ClientMain.ClientServerConnector;
import com.spiralSpotManagement.Server.DbController.CloudStorageConnectionHandler;
import com.spiralSpotManagement.Server.Model.BillingModel;
import com.spiralSpotManagement.Server.Model.ResponseStatus;
import com.spiralSpotManagement.Server.Model.UserCategory;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BilllingActions {

    /**
     *  Register a new billing plan.
     *  The function will receive an object of a plan directly insert into table of billing
     * @return an Object of response
     * @param 'BillingInfo Object'
     * @author Gervais Ishimwe

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


    /**
     *  Update a billing plan.
     *  The function will receive an object of a plan and update that plan accordingly
     * @param 'BillingInfo Object'
     * @return an Object of response
     * @author Gervais Ishimwe


     */

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

    public List<Object> getAllBillingPLan()throws Exception{

        String query = "SELECT * FROM billing";
        List<Object> planList = new ArrayList<>();
        try{
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            Statement stmt = connection.createStatement();
            ResultSet planResult =stmt.executeQuery(query);

            while(planResult.next()){

                BillingModel billingPlan = new BillingModel();
                billingPlan.setBilling_id(planResult.getInt(1));
                billingPlan.setBilling_name(planResult.getString(2));
                billingPlan.setPrice(planResult.getInt(3));
                billingPlan.setBilling_period(planResult.getInt(4));
                billingPlan.setBilling_status(planResult.getString(5));

                planList.add(billingPlan);
            }

            return planList;


        }catch (Exception e){
            planList.add(e);
        }
    return  null;

    }

    public List<Object> getBillingPlanById(BillingModel billingModel)throws Exception{

        String query = "SELECT * FROM billing WHERE billing_id="+billingModel.getBilling_id();
        List<Object> planList = new ArrayList<>();
        try{
            Connection connection = new CloudStorageConnectionHandler().getConnection();

            Statement stmt = connection.createStatement();
            ResultSet planResult =stmt.executeQuery(query);

            while(planResult.next()){

                BillingModel billingPlan = new BillingModel();
                billingPlan.setBilling_id(planResult.getInt(1));
                billingPlan.setBilling_name(planResult.getString(2));
                billingPlan.setPrice(planResult.getInt(3));
                billingPlan.setBilling_period(planResult.getInt(4));
                billingPlan.setBilling_status(planResult.getString(5));

                planList.add(billingPlan);
            }

            return planList;


        }catch (Exception e){
            planList.add(e);
        }
        return  null;

    }


}
