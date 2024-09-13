module lightgroup.lights {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens lightgroup.lights to javafx.fxml;
    exports lightgroup.lights;
}