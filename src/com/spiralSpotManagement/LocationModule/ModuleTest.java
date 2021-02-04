package com.spiralSpotManagement.LocationModule;

import java.util.HashMap;
import java.util.List;

/**
 * This class is created for testing The whole location module
 * It extends <b>Location</b> class
 *
 * If You are using this location module, this class will serve as a documentation.
 *
 * @author Harerimana Egide
 * @since 2021-02-04
 * @version 1.0
 */
public class ModuleTest {

    private final Location location = new Location();

    public void testDbConnection(){
        try {
            location.checkDbWorking(location.getConnection());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckCreateLocationLevelsTable(){
        try {
            location.createLocationLevelsTable();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckCreateLocationsTable(){
        try {
            location.createLocationsTable();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckGetAllLocationsLevels(){
        try {
            List<HashMap> resultList = location.getAllLocationsLevels();
            System.out.println(resultList);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void CheckRegisterLocationLevel(){
        try {
            String new_level_id = location.newLocationLevel("country");
            System.out.println(new_level_id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void checkRegisterLocation(){
        try {
            String new_location_id = location.newLocation("Nyabihu District","(1,2,3,4)", "Nyabihu district located in north rwanda", "2089b2b6-1b6e-46d0-8cde-3f532c4e9210", "");
            System.out.println(new_location_id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        /**
         *  ModuleTest test = new ModuleTest();
         *  test.CheckCreateLocationsTable();
         *  test.checkRegisterLocation();
         * test.CheckGetAllLocationsLevels();
         */

    }
}
//
//    CREATE TABLE `notifications` (
//        `notification_id` varchar(255) NOT NULL,
//        `user_id` varchar(255) NOT NULL,
//        `receiver_id` varchar(255) NOT NULL,
//        `created_at` DATE NOT NULL,
//        `message_header` varchar(255) NOT NULL,
//        `message_body` TEXT NOT NULL,
//        PRIMARY KEY (`notification_id`)
//        );
//
//        CREATE TABLE `user categories` (
//        `category id` varchar(255) NOT NULL,
//        `category_name` varchar(255) NOT NULL,
//        `description` TEXT NOT NULL,
//        PRIMARY KEY (`category id`)
//        );
//
//        CREATE TABLE `spot` (
//        `spot_id` varchar(255) NOT NULL,
//        `category_id` varchar(255) NOT NULL,
//        `location_id` varchar(255) NOT NULL,
//        `name` varchar(255) NOT NULL,
//        `desciption` TEXT NOT NULL,
//        `views` INT NOT NULL,
//        `viewers` INT NOT NULL,
//        `registrationDate` DATE NOT NULL,
//        `updateDate` DATE NOT NULL,
//        `moreInfo` TEXT NOT NULL,
//        PRIMARY KEY (`spot_id`)
//        );
//
//        CREATE TABLE `spot categories` (
//        `category_id` varchar(255) NOT NULL,
//        `created_by` varchar(255) NOT NULL,
//        `category_name` varchar(255) NOT NULL,
//        `description` TEXT(255) NOT NULL,
//        PRIMARY KEY (`category_id`)
//        );
//
//        CREATE TABLE `comments` (
//        `comment_id` varchar(255) NOT NULL,
//        `spot_id` varchar(255) NOT NULL,
//        `user_id` varchar(255) NOT NULL,
//        `reply_id` varchar(255) NOT NULL,
//        `created_by` varchar(255) NOT NULL,
//        `reply` varchar(255) NOT NULL,
//        `content` varchar(255) NOT NULL,
//        `status` varchar(255) NOT NULL,
//        `created_at` DATE NOT NULL,
//        `updated_at` DATE NOT NULL,
//        `likes` varchar(255) NOT NULL,
//        `dislikes` varchar(255) NOT NULL,
//        PRIMARY KEY (`comment_id`)
//        );
//
//        CREATE TABLE `location` (
//        `location_id` varchar(255) NOT NULL,
//        `level_id` varchar(255) NOT NULL,
//        `parent_id` varchar(255),
//        `location_name` varchar(255) NOT NULL,
//        `location_GPS` varchar(255) NOT NULL,
//        `description` TEXT NOT NULL,
//        PRIMARY KEY (`location_id`)
//        );
//
//        CREATE TABLE `location levels` (
//        `level_id` varchar(255) NOT NULL,
//        `level_name` varchar(255) NOT NULL,
//        PRIMARY KEY (`level_id`)
//        );
//
//        CREATE TABLE `spot ratings` (
//        `rating_id` varchar(255) NOT NULL,
//        `user_id` varchar(255) NOT NULL,
//        `spot_id` varchar(255) NOT NULL,
//        `rating` INT NOT NULL,
//        PRIMARY KEY (`rating_id`)
//        );
//
//        CREATE TABLE `users` (
//        `user_id` varchar(255) NOT NULL,
//        `category_id` varchar(255) NOT NULL,
//        `location_id` varchar(255) NOT NULL,
//        `first_name` varchar(255) NOT NULL,
//        `last_name` varchar(255) NOT NULL,
//        `user_name` varchar(255) NOT NULL,
//        `email` varchar(255) NOT NULL,
//        `gender` varchar(255) NOT NULL,
//        `birth_date` DATE NOT NULL,
//        `password` varchar(255) NOT NULL,
//        PRIMARY KEY (`user_id`)
//        );
//
//        CREATE TABLE `user activities` (
//        `activity_id` varchar(255) NOT NULL,
//        `user_id` varchar(255) NOT NULL,
//        `last_login` DATE NOT NULL,
//        `last_logout` DATE NOT NULL,
//        `action` TEXT NOT NULL,
//        PRIMARY KEY (`activity_id`)
//        );
//
//        CREATE TABLE `user reports statistics` (
//        `id` varchar(255) NOT NULL,
//        `action` varchar(255) NOT NULL,
//        `total_users` INT NOT NULL,
//        PRIMARY KEY (`id`)
//        );
//
//        ALTER TABLE `notifications` ADD CONSTRAINT `notifications_fk0` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);
//
//        ALTER TABLE `notifications` ADD CONSTRAINT `notifications_fk1` FOREIGN KEY (`receiver_id`) REFERENCES `users`(`user_id`);
//
//        ALTER TABLE `spot` ADD CONSTRAINT `spot_fk0` FOREIGN KEY (`category_id`) REFERENCES `spot categories`(`category_id`);
//
//        ALTER TABLE `spot` ADD CONSTRAINT `spot_fk1` FOREIGN KEY (`location_id`) REFERENCES `location`(`location_id`);
//
//        ALTER TABLE `spot categories` ADD CONSTRAINT `spot categories_fk0` FOREIGN KEY (`created_by`) REFERENCES `users`(`user_id`);
//
//        ALTER TABLE `comments` ADD CONSTRAINT `comments_fk0` FOREIGN KEY (`spot_id`) REFERENCES `spot`(`spot_id`);
//
//        ALTER TABLE `comments` ADD CONSTRAINT `comments_fk1` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);
//
//        ALTER TABLE `comments` ADD CONSTRAINT `comments_fk2` FOREIGN KEY (`reply_id`) REFERENCES `comments`(`comment_id`);
//
//        ALTER TABLE `comments` ADD CONSTRAINT `comments_fk3` FOREIGN KEY (`created_by`) REFERENCES `users`(`user_id`);
//
//        ALTER TABLE `location` ADD CONSTRAINT `location_fk0` FOREIGN KEY (`level_id`) REFERENCES `location levels`(`level_id`);
//
//        ALTER TABLE `location` ADD CONSTRAINT `location_fk1` FOREIGN KEY (`parent_id`) REFERENCES `location`(`location_id`);
//
//        ALTER TABLE `spot ratings` ADD CONSTRAINT `spot ratings_fk0` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);
//
//        ALTER TABLE `spot ratings` ADD CONSTRAINT `spot ratings_fk1` FOREIGN KEY (`spot_id`) REFERENCES `spot`(`spot_id`);
//
//        ALTER TABLE `users` ADD CONSTRAINT `users_fk0` FOREIGN KEY (`category_id`) REFERENCES `user categories`(`category id`);
//
//        ALTER TABLE `users` ADD CONSTRAINT `users_fk1` FOREIGN KEY (`location_id`) REFERENCES `location`(`location_id`);
//
//        ALTER TABLE `user activities` ADD CONSTRAINT `user activities_fk0` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);
