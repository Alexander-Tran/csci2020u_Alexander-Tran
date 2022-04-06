package com.example.lab10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Client stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Lab 10 Solution - Client");
        stage.setScene(scene);
        stage.show();

        //Server stage
        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("server.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 420, 340);
        stage2.setTitle("Lab 10 Solution - Server");
        stage2.setScene(scene2);
        stage2.show();
    }

    public static void main(String[] args) {
        launch();
    }
}