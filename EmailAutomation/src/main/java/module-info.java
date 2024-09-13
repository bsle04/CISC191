module com.example.emailautomation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.emailautomation to javafx.fxml;
    exports com.example.emailautomation;
}