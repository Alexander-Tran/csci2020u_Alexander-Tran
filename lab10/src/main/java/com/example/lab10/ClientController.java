package com.example.lab10;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class ClientController {
    @FXML private TextField usernameField;
    @FXML private TextField messageField;

    @FXML
    protected void handleSendButton() {
        try {
            //Create new socket connected to server
            Socket socket = new Socket("localhost", 6666);

            //Send message to server
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(usernameField.getText() + ": " + messageField.getText());
            writer.flush();
            //usernameField.clear();
            messageField.clear();

            //Close socket
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Exit button
    @FXML
    protected void handleExitButton() {
        Platform.exit();
    }
}
