module com.example.lab05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab05 to javafx.fxml;
    exports com.example.lab05;
}