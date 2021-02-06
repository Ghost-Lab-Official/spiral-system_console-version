package com.spiralSpotManagement.ReportModule;

import java.util.Date;

public class usersModel {
    private String user_id;
    private String first_name;
    private String last_name;
    private String user_name;
    private String email;
    private String gender;
    private Date birth_date;
    private String user_category;
    private String location;
    private String user_status;
    private Date registration_date;

    public usersModel(String user_id,
                      String first_name,
                      String last_name,
                      String user_name,
                      String email,
                      String gender,
                      Date birth_date,
                      String user_category,
                      String location,
                      String user_status,
                      Date registration_date) {
        this.user_id=user_id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.user_name=user_name;
        this.email=email;
        this.gender=gender;
        this.birth_date=birth_date;
        this.user_category=user_category;
        this.location=location;
        this.user_status=user_status;
        this.registration_date=registration_date;
    }


    public String getUserCategoryName() {
        return user_category;
    }

    public String getLocationName() {
        return location;
    }

    public Date getRegistrationDate() {
        return registration_date;
    }

    public String getUserId() {
        return user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getUserStatus() {
        return user_status;
    }

    public Date getBirthDate() {
        return birth_date;
    }

    public void setUserCategoryName(String user_category) {
        this.user_category = user_category;
    }

    public void setLocationName(String location) {
        this.location = location;
    }

    public void setRegistrationDate(Date registration_date) {
        this.registration_date = registration_date;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserStatus(String user_status) {
        this.user_status = user_status;
    }

    public void setBirthDate(Date birth_date) {
        this.birth_date = birth_date;
    }






}
