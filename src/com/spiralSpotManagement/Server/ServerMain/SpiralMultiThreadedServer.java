package com.spiralSpotManagement.Server.ServerMain;

import com.spiralSpotManagement.Server.Controllers.UserModuleControllers.UserController;
import com.spiralSpotManagement.Server.Model.RequestBody;
import com.spiralSpotManagement.Server.Model.Users;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Server class
public class SpiralMultiThreadedServer {
    public static void main(String[] args)
    {
        ServerSocket server = null;

        try {

            // server is listening on port 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {
                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            ObjectOutputStream out = null;
            ObjectInputStream in = null;
            try {
                // get the outputstream of client
                out = new ObjectOutputStream(
                        clientSocket.getOutputStream());

                // get the inputstream of client
                in = new ObjectInputStream(clientSocket.getInputStream());

                RequestBody requestBody;
                while ((requestBody = (RequestBody) in.readObject()) != null) {
                    //Reading the url
                    String url = requestBody.getUrl();

                    List<Object> responseObject = null;
                    switch (url){
                        case "/users":

                            responseObject =  new UserController().mainMethod(requestBody);
                            break;

                        case "/spot":

                            break;

                        case "sport-category":
                            break;

                        case "/search":
                            break;

                        case "/report":
                            break;

                        case "/locations":
                            break;

                        case "/billing":
                            break;

                        default:

                    }
                    // writing the received message from
                    // client
//                   Users users = new Users();
//                    users.setEmail(((Users) requestBody.getObject()).getEmail());
//                    System.out.println(users.getEmail());
////                    System.out.printf(
////                            " Sent from the client: %s\n",
////                            requestBody.getObject());
//                    List<Users> usersList = new ArrayList<>();
//                    usersList.add(users);
//                    usersList.add(users);

                    out.writeObject(responseObject);
                }
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
