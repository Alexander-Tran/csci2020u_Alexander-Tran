module com.example.midterm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.midterm to javafx.fxml;
    exports com.example.midterm;
}