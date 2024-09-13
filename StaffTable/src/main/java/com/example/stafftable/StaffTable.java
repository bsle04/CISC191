package com.example.stafftable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffTable extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Music Controls");


        Button playButton = new Button("Play");
        Button loopButton = new Button("Loop");
        Button stopButton = new Button("Stop");

        HBox hbox = new HBox(2);
        hbox.getChildren().addAll(playButton, loopButton, stopButton);
        hbox.setAlignment(Pos.CENTER);

        Media media = new Media("file:///C:/Users/Brandon/Downloads/t-2it9iWMgDGaafHYLQQMYbFwv8tw6BQmW.m4a");
        MediaPlayer mediaPlayer = new MediaPlayer(media);



        playButton.setOnAction(value ->  {
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        });

        stopButton.setOnAction(value ->  {
            mediaPlayer.stop();
        });

        loopButton.setOnAction(value ->  {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        });

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}