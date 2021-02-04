package com.spiralSpotManagement.Client.ClientMain;

import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.ResponseBody;
import com.spiralSpotManagement.Server.Model.Users;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientServerConnector {
    public ResponseBody ConnectToServer(RequestBody requestBody)throws Exception
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream());

            // reading from server
            ObjectInputStream in
                    = new ObjectInputStream(socket.getInputStream());

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

//            while (!"exit".equalsIgnoreCase(line)) {
//                RequestBody myRequestBody = new RequestBody("/users","/post",users);
//                Users userSent = (Users) myR                System.out.println(" add name");equestBody.getObject();
//                userSent.getEmail();
                /*
                        SAMPLE OF THE REQUEST AND RESPONSE
                        ----------------------------------
                        user: /users
                        response : table of users

                        user: /users/1
                        response : user with id that id
                 */

                // sending the user input to server
                out.writeObject(requestBody);
                out.flush();

                // displaying server reply
                 List<Object> dataReturned = (List<Object>) in.readObject();
//                 for (Users user:usersFound){
//                     System.out.println("Server replied "
//                             + user.getEmail());
//                 }
//            }

            // closing the scanner object
            sc.close();

            ResponseBody responseBody = new ResponseBody(dataReturned);

            return responseBody;

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
