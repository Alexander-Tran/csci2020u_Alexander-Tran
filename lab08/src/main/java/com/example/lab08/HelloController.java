package com.example.lab08;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.lab08.DataSource.getAllMarks;

public class HelloController implements Initializable {
    @FXML private TableView<StudentRecord> tableView;
    @FXML public TableColumn<StudentRecord, String> studentID;
    @FXML public TableColumn<StudentRecord, Float> assignments;
    @FXML public TableColumn<StudentRecord, Float> midterm;
    @FXML public TableColumn<StudentRecord, Float> finalExam;
    @FXML public TableColumn<StudentRecord, Float> finalMark;
    @FXML public TableColumn<StudentRecord, Character> letterGrade;

    //Initialize table with DataSource data
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        ObservableList<StudentRecord> record = getAllMarks();
        for(int i = 0; i < record.size(); ++i) {
            tableView.getItems().add(record.get(i));
        }
    }

    //Bonus: Add new values to table
    @FXML private TextField SIDField;
    @FXML private TextField assignmentsField;
    @FXML private TextField midtermField;
    @FXML private TextField finalExamField;

    @FXML
    protected void handleAddButton() {
        //Convert TextField strings to floats
        //Note: Does not handle invalid input
        float assignment = Float.parseFloat(assignmentsField.getText());
        float midterm = Float.parseFloat(midtermField.getText());
        float finalExam = Float.parseFloat(finalExamField.getText());

        //Add values to table
        tableView.getItems().add(new StudentRecord(SIDField.getText(), assignment, midterm, finalExam));

        SIDField.clear();
        assignmentsField.clear();
        midtermField.clear();
        finalExamField.clear();
    }


    // Lab 08
    //Filename variable
    @FXML public String currentFilename = "data.csv";

    //Save function
    public void save() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(currentFilename);

        for(int i = 0; i < tableView.getItems().size(); ++i) {
            String text = "";
            text += tableView.getItems().get(i).getStudentID() + ",";
            text += tableView.getItems().get(i).getAssignments() + ",";
            text += tableView.getItems().get(i).getMidterm() + ",";
            text += tableView.getItems().get(i).getFinalExam() + "\n";
            output.write(text);
        }
        output.close();
    }

    //Load function
    public void load() throws IOException {
        FileReader fileReader = new FileReader(currentFilename);
        BufferedReader input = new BufferedReader(fileReader);
        String text = null;
        String csvData = "";
        while((text = input.readLine()) != null) {
            csvData += text + "\n";
        }

        //Add csv data
        String[] rows = csvData.split("\n");
        for(int i = 0; i < rows.length; ++i) {
            String[] cells = rows[i].split(",");
            tableView.getItems().add(new StudentRecord(cells[0], Float.parseFloat(cells[1]),
                    Float.parseFloat(cells[2]), Float.parseFloat(cells[3])));
        }
    }

    //Clear all items from TableView
    @FXML protected void handleNewItem() {
        tableView.getItems().clear();
    }

    //Load csv file
    @FXML protected void handleOpenItem() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        currentFilename = selectedFile.getName();
        tableView.getItems().clear();
        load();
    }

    //Save data in current file
    @FXML protected void handleSaveItem() throws FileNotFoundException {
        save();
    }

    //Save data to file
    @FXML protected void handleSaveAsItem() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);
        currentFilename = selectedFile.getName();
        save();
    }

    //Exit
    @FXML protected void handleExitItem() {
        Platform.exit();
    }
}