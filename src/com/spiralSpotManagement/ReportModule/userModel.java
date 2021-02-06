package com.spiralSpotManagement.ReportModule; // user model by Nicole

import java.util.Date;

public class userModel {
    private int user_id;
    private String first_name;
    private String last_name;
    private String user_name;
    private String email;
    public String gender;
    private Date birth_date;
    private String password;
    private double user_category;
    private String location;
    private String user_status;
    private Date registration_date;


    public userModel(int user_id,
                      String first_name,
                      String last_name,
                      String user_name,
                      String email,
                      String gender,
                      Date birth_date,
                      String password,
                      double user_category,
                      String location,
                      String user_status,
                      Date registration_date) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.email = email;
        this.gender = gender;
        this.birth_date = birth_date;
        this.password = password;
        this.user_category = user_category;
        this.location = location;
        this.user_status = user_status;
        this.registration_date = registration_date;
    }


    public int getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name(){ return last_name; }

    public String getUser_name(){ return  user_name; }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getPassword() {
        return password;
    }

    public Double getUser_category() {
        return user_category;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return user_status;
    }

    public Date getRegistration_date() {
        return registration_date;
    }





    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name){ this.user_name = first_name; }

    public void setLast_name(String last_name){ this.last_name = last_name; }

    public void setUser_name(String user_name){ this.user_name = user_name; }

    public void setEmail(String email){ this.email = email; }

    public void setGender(String gender){ this.gender = gender; }

    public void setBirth_date(Date birth_date){ this.birth_date = birth_date; }

    public void setPassword(String password){ this.user_name = password; }

    public void setUser_category(Double user_category){ this.user_category = user_category; }

    public void setLocation(String location){ this.location = location; }

    public void setStatus(String user_status) { this.user_status = user_status; }

    public void setRegistration_date(Date registration_date) { this.registration_date = registration_date; }


}