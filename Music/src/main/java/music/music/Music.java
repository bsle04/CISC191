package music.music;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.media.*;
import javafx.stage.Stage;


public class Music extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Music Controls");


        ToggleButton playButton = new ToggleButton("Play");
        ToggleButton loopButton = new ToggleButton("Loop");
        ToggleButton stopButton = new ToggleButton("Stop");

        HBox hbox = new HBox(2);
        hbox.getChildren().addAll(playButton, loopButton, stopButton);
        hbox.setAlignment(Pos.CENTER);

        AudioClip audio = new AudioClip("t-2it9iWMgDGaafHYLQQMYbFwv8tw6BQmW");




        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}