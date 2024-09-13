module music.music {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens music.music to javafx.fxml;
    exports music.music;
}