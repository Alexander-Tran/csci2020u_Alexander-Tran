package com.example.lab10;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    @FXML private TextArea textArea;

    public void initialize(URL location, ResourceBundle resources) {
        textArea.setEditable(false);

        //Create new thread for server
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Create server
                    ServerSocket server = new ServerSocket(6666);
//                    System.out.println("Server online");

                    //Accept connections, add messages to text area
                    while(true) {
                        Socket socket = server.accept();
                        BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String message;
                        while((message = inStream.readLine()) != null) {
                            textArea.appendText(message + "\n\n");
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
    }

    //Exit button
    @FXML
    protected void handleExitButton() throws IOException {
        Platform.exit();
    }
}
