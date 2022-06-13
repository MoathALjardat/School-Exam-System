module com.example.usablesecurityproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.usablesecurityproject to javafx.fxml;
    exports com.example.usablesecurityproject;
}