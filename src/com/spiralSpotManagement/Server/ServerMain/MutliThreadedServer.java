package com.spiralSpotManagement.Server.ServerMain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Server class
public class MutliThreadedServer {
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

                MultiThreadedClient.RequestBody requestBody;
                while ((requestBody = (MultiThreadedClient.RequestBody) in.readObject()) != null) {
                    //Reading the url
//                    String url = requestBody.getUrl();
//                    switch (url){
//                        case "/users":
//
//                            break;
//
//                        case "/spot":
//
//                            break;
//
//                        case "sport-category":
//                            break;
//
//                        case "/search":
//                            break;
//
//                        case "/report":
//                            break;
//
//                        case "/locations":
//                            break;

//                        case "/billing":
//                            break;
//
//                        default:
//
//                            break;
//
//
//                    }
                    // writing the received message from
                    // client
                    MultiThreadedClient.Users users = new MultiThreadedClient.Users();
                    users.setEmail(((MultiThreadedClient.Users) requestBody.getObject()).getEmail());
                    System.out.println(users.getEmail());
//                    System.out.printf(
//                            " Sent from the client: %s\n",
//                            requestBody.getObject());
                    List<MultiThreadedClient.Users> usersList = new ArrayList<>();
                    usersList.add(users);
                    usersList.add(users);

                    out.writeObject(usersList);
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
