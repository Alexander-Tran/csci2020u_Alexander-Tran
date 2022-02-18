package com.example.lab04;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField fullName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker date;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label dateLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Label output;

    //Format Checker
    protected boolean checkFormat(String fullName, String email, String phone, DatePicker date) {
        String fullNameRegex = "^(.+) (.+)$";
        Pattern fullNamePattern = Pattern.compile(fullNameRegex);
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        if(!fullNameMatcher.matches()) {
            fullNameLabel.setText("Invalid Full Name");
        }
        else {
            fullNameLabel.setText("");
        }

        String emailRegex = "^(.+)@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.matches()) {
            emailLabel.setText("Invalid E-Mail Address");
        }
        else {
            emailLabel.setText("");
        }

        String phoneRegex = "^(\\d{3})-(\\d{3})-(\\d{4})$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(phone);
        if(!phoneMatcher.matches()) {
            phoneLabel.setText("Invalid Phone #");
        }
        else {
            phoneLabel.setText("");
        }

        boolean validDate = false;
        if(!(date.getValue() instanceof LocalDate)) {
            dateLabel.setText("Invalid Date of Birth");
        }
        else {
            validDate = true;
            dateLabel.setText("");
        }

        if(fullNameMatcher.matches() && emailMatcher.matches() &&
           phoneMatcher.matches() && validDate) {
            return true;
        }
        return false;
    }

    @FXML
    protected void handleRegisterButton() {
        checkFormat(fullName.getText(), email.getText(), phone.getText(), date);

        if(checkFormat(fullName.getText(), email.getText(), phone.getText(), date)) {
            output.setText("Username: " + username.getText() + "\nPassword: " +
                    password.getText() + "\nFull Name: " + fullName.getText() +
                    "\nE-Mail: " + email.getText() + "\nPhone #: " + phone.getText() +
                    "\nDate of Birth: " + date.getValue());
            username.clear();
            password.clear();
            fullName.clear();
            email.clear();
            phone.clear();
            date.getEditor().clear();
        }
    }
}