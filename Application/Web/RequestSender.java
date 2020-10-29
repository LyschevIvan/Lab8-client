package com.company.Application.Web;

import com.company.Application.Data;
import com.company.Application.Exceptions.NoConnectionException;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Spliterator;

public class RequestSender {
    private  ObjectOutputStream objectOutputStream;
    private  OutputStream socketOutput;
    public RequestSender(Socket socket) throws IOException {
        socketOutput = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(socketOutput);

    }

    public void sendRequest(Data data) {
        try{
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            socketOutput.flush();
        } catch (IOException e) {

//            try {
////                connect();
//            } catch (IOException ioException) {
                System.out.println("Нет подключения");
                System.exit(1);
//
//            }
        }

    }
//    void connect() throws IOException {
//        boolean notConnected = true;
//        int count =0;
//        while(notConnected && count < 5){
//            try{
//                Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),8989);
//                socketOutput = socket.getOutputStream();
//                objectOutputStream = new ObjectOutputStream(socketOutput);
//                notConnected = false;
//            }
//            catch (ConnectException e){
//                count++;
//            }
//
//        }
//
//    }
}
