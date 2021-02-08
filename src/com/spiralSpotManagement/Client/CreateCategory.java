//package com.spiralSpotManagement.Client;
//
//
//import java.sql.*;
//import java.util.Scanner;
//
//
//public class CreateCategory {
//
//    private static int catId;
//    private static String catName;
//
//    public CreateCategory(){}
//
//    public CreateCategory(String catName){
//        this.catName=catName;
//    }
//
//    public int getCatId() { return catId; }
//    public void setCatId(int catId) { this.catId = catId; }
//    public String getCatName() { return catName; }
//    public void setCatName(String catName) { this.catName = catName; }
//
//
//
//    private static final String InsertSql = "INSERT INTO users_categories (user_category) VALUES(?)";
//    private static final String UpdateSql = "UPDATE users_categories SET user_category=? WHERE category_id=?";
//    private static final String deleteSQL = "DELETE FROM users_categories where category_id=?";
//
//
//
////    Insert category function
//
//    public static void insertCategory(Connection connection) throws SQLException{
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Insert the Category Name");
//        String category =scan.nextLine();
//        CreateCategory category1 = new CreateCategory(category);
//        try (PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)){
//            preparedStatement.setString(1,category1.getCatName());
//            preparedStatement.execute();
//            System.out.println(preparedStatement);
//        }
//        catch (SQLException a){
//            a.printStackTrace();
//        }
//    }
//
////    End of insert category
//
//    //  select category function
//    public static void selectCategory(Connection connection)throws Exception{
//        Statement state= connection.createStatement();
//        ResultSet result =state.executeQuery("SELECT * FROM users_categories");
//        System.out.println("\t\t\t Categories\t\n");
//        System.out.println("\t ID  \t Category name\n");
//        while (result.next()){
//            System.out.println("\t "+result.getString(1)+" \t\t "+result.getString(2));
//        }
//    }
//
////    End of select category
//
//    //    Update category function
//    public static void UpdateCategory(Connection connection) throws Exception{
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Insert the Category Name");
//        String category =scan.nextLine();
//        System.out.println("Insert the category Id");
//        int id = scan.nextInt();
//        CreateCategory category1 = new CreateCategory(category);
//        try (PreparedStatement sql = connection.prepareStatement(UpdateSql)){
//            sql.setString(1,category1.getCatName());
//            sql.setInt(2,id);
//            sql.execute();
//            System.out.println(sql);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
////    End of Update
//
//    //delete function
//    public static void deleteCategory(Connection connection) throws Exception{
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Insert category id");
//        int id = scan.nextInt();
//        try (PreparedStatement sql= connection.prepareStatement(deleteSQL)){
//            sql.setInt(1,id);
//            sql.execute();
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
////    end of delete function
//
//    //    main function
//    public static void main(String[] args) throws Exception {
//        Scanner input = new Scanner(System.in);
//        System.out.println("==================================");
//        System.out.println("||\t\tUser Category Section\t||\n");
//        System.out.println("||\t\t1.Create Category\t\t||\n");
//        System.out.println("||\t\t2.Edit Category  \t\t||\n");
//        System.out.println("||\t\t3.View Categories\t\t||\n");
//        System.out.println("||\t\t4.Delete Category\t\t||\n");
//        System.out.println("||\t\t5.Exit           \t\t||\n");
//        System.out.println("==================================");
//        String choose =input.nextLine();
//
//        CloudStorageConnection cloudStorageConnection = new CloudStorageConnection();
//
//        switch (choose){
//            case "1":
//                insertCategory(cloudStorageConnection.getConnection());
//                break;
//            case "2":
//                UpdateCategory(cloudStorageConnection.getConnection());
//                break;
//            case "3":
//                selectCategory(cloudStorageConnection.getConnection());
//                break;
//            case "4":
//                deleteCategory(cloudStorageConnection.getConnection());
//                break;
//            case "5":
//                System.exit(0);
//            default:
//                System.out.println("Incorrect Input!!");
//        }
//    }
//}