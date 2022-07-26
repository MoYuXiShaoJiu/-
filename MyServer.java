package com.example.aircraftwar;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyServer {
   public MyServer(){
       {
           try {
               InetAddress addr = InetAddress.getLocalHost();
           } catch (UnknownHostException e) {
               e.printStackTrace();
           }
       }
       //创建sever socket
       ServerSocket serverSocket;

       {
           try {
               serverSocket = new ServerSocket(9999);
               while (true)
               {
                   System.out.println("waiting client connect");
                   Socket socket = serverSocket.accept();
                   System.out.println("accept client connect" + socket);

               }

           }
           catch (IOException e) {
               e.printStackTrace();
           }
       }

   }






}
