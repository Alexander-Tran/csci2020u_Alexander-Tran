package com.example.midterm;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class HelloController {
    /**
     * Changes scene to animation
     * @param event event
     * @throws IOException IOException
     */
    @FXML
    protected void animationButton(ActionEvent event) throws IOException {
        //Create circle
        Circle circle = new Circle(50, 150, 50, Color.RED);

        //Set path
        Path path = new Path();
        //Starting position
        MoveTo moveTo = new MoveTo(0+50, 150);
        //Left to right
        HLineTo line1 = new HLineTo();
        line1.setX(320-50);
        //Right to left
        HLineTo line2 = new HLineTo();
        line2.setX(0+50);

        //Add path elements
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2);

        //Set path transition
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(4));
        pathTransition.setNode(circle);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);

        //Playing the animation
        pathTransition.play();

        //Back button
        Button button = new Button("Back to Main");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    backButton(e);
                }
                catch (IOException ex) {
                }
            }
        });

        //Create and display stage
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Group root = new Group(circle, button);
        Scene scene = new Scene(new Pane(root), 320, 300);
        stage.setTitle("Animation");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Changes scene to graphics
     * @param event event
     * @throws IOException IOException
     */
    @FXML
    protected void graphicsButton(ActionEvent event) throws IOException {
        //Draw initials
        Canvas canvas = new Canvas(280, 280);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Draw shapes
        //Formatting variable
        int k = 35;
        //A
        gc.setStroke(Color.RED);
        gc.strokeLine(80+k, 200, 100+k, 120);
        gc.strokeLine(100+k, 120, 120+k, 200);
        gc.strokeLine(90+k, 160, 110+k, 160);
        //.
        gc.setStroke(Color.BLACK);
        gc.strokeRect(125+k,195,2,2);
        //T
        gc.setStroke(Color.BLUE);
        gc.strokeLine(130+k, 120, 170+k, 120);
        gc.strokeLine(150+k, 120, 150+k, 200);

        Label label = new Label("A.T");
        label.setTranslateX(150);

        //Back button
        Button button = new Button("Back to Main");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    backButton(e);
                }
                catch (IOException ex) {
                }
            }
        });

        //Create and display stage
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Group root = new Group(canvas, label, button);
        Scene scene = new Scene(root, 320, 300);
        stage.setTitle("Animation");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Changes scene to about
     * @param event event
     * @throws IOException IOException
     */
    @FXML
    protected void aboutButton(ActionEvent event) throws IOException, ParserConfigurationException, SAXException {
        //Read xml file
        File file = new File("src/main/resources/com/example/midterm/about.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        //Read xml file, store name and email
        String id = "";
        String name = "";
        String email = "";
        NodeList itemNodes = document.getElementsByTagName("student");
        for(int i = 0; i < itemNodes.getLength(); ++i) {
            org.w3c.dom.Node itemNode = itemNodes.item(i);
            if(itemNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element element = (Element)itemNode;
                id = element.getAttribute("id");
                name = element.getElementsByTagName("name").item(0).getTextContent();
                email = element.getElementsByTagName("email").item(0).getTextContent();
            }
        }

        //Store software description
        NodeList description1 = document.getElementsByTagName("software-description");
        org.w3c.dom.Node descriptionNode = description1.item(0);
        Element element = (Element)descriptionNode;
        String softwareDescription = element.getTextContent();

        //Create labels
        Label descriptionLabel1 = new Label("Software\nDescription:");
        Label descriptionLabel = new Label(softwareDescription);
        Label nameLabel1 = new Label("Name");
        Label nameLabel = new Label(name);
        Label emailLabel1 = new Label("Email:");
        Label emailLabel = new Label(email);
        Label idLabel1 = new Label("Student ID:");
        Label idLabel = new Label(id);

        //Back button
        Button button = new Button("Back to Main");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    backButton(e);
                }
                catch (IOException ex) {
                }
            }
        });

        //Store data in GridPane
        GridPane gridpane = new GridPane();
        gridpane.add(button, 0, 0);
        gridpane.add(descriptionLabel1, 0, 1);
        gridpane.add(descriptionLabel, 1, 1);
        gridpane.add(idLabel1, 0, 2);
        gridpane.add(idLabel, 1, 2);
        gridpane.add(nameLabel1, 0, 3);
        gridpane.add(nameLabel, 1, 3);
        gridpane.add(emailLabel1, 0, 4);
        gridpane.add(emailLabel, 1, 4);
        gridpane.setVgap(10);

        //Create and display stage
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(gridpane, 320, 300);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Changes scene back to main
     * @param event event
     * @throws IOException IOException
     */
    protected void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 320, 300);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
}