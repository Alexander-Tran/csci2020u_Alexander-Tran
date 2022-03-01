package com.example.lab05;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.lab05.DataSource.getAllMarks;

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
}