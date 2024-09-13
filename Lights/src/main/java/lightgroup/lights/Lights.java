package lightgroup.lights;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;

public class Lights extends Application {
    public void start(Stage primaryStage) {

        VBox paneForCircles = new VBox(5);
        paneForCircles.setAlignment(Pos.CENTER);

        Circle c1 = getCircle();
        Circle c2 = getCircle();
        Circle c3 = getCircle();

        paneForCircles.getChildren().addAll(c1, c2, c3);

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setWidth(30);
        rectangle.setHeight(100);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        StackPane trafficLight = new StackPane(rectangle, paneForCircles);

        HBox buttons = new HBox(5);
        buttons.setAlignment(Pos.CENTER);

        RadioButton redButton = new RadioButton("Red");
        RadioButton yellowButton = new RadioButton("Yellow");
        RadioButton greenButton = new RadioButton("Green");

        ToggleGroup group = new ToggleGroup();
        redButton.setToggleGroup(group);
        yellowButton.setToggleGroup(group);
        greenButton.setToggleGroup(group);
        buttons.getChildren().addAll(redButton, yellowButton, greenButton);

        BorderPane pane = new BorderPane();
        pane.setCenter(trafficLight);
        pane.setBottom(buttons);

        redButton.setOnAction(e -> {
            if (redButton.isSelected()) {
                c1.setFill(Color.RED);
                c2.setFill(Color.WHITE);
                c3.setFill(Color.WHITE);
            }
        });

        yellowButton.setOnAction(e -> {
            if (yellowButton.isSelected()) {
                c1.setFill(Color.WHITE);
                c2.setFill(Color.YELLOW);
                c3.setFill(Color.WHITE);
            }
        });

        greenButton.setOnAction(e -> {
            if (greenButton.isSelected()) {
                c1.setFill(Color.WHITE);
                c2.setFill(Color.WHITE);
                c3.setFill(Color.GREEN);
            }
        });

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Traffic Light");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private Circle getCircle() {
        Circle c = new Circle(10);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        return c;
    }
}