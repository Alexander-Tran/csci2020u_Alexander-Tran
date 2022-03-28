module com.example.lab08 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab08 to javafx.fxml;
    exports com.example.lab08;
}