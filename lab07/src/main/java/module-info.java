module com.example.lab07 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab07 to javafx.fxml;
    exports com.example.lab07;
}