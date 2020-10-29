package com.company;

import com.company.Application.GUI.TableController;
import com.company.Application.Web.RequestSender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TableForm extends Application {
    private RequestSender requestSender;


    @Override
    public void start(Stage primaryStage)  {

        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(),8987);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/company/Application/GUI/tableScene.fxml"));
            TableController tableController = new TableController(socket);
            loader.setController(tableController);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setRoot(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Лабораторная работа 8");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Не удается подключиться к серверу");
            System.exit(1);
        }


    }
    public static void main(String[] args){
        launch(args);

    }
}
