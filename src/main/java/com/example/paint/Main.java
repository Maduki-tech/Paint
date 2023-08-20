package com.example.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    Canvas canvas = new Canvas(800, 800);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Paint");
        PencilDrawingController pencilDrawing = new PencilDrawingController(gc, canvas);
        Button clearButton = new Button("Clear");
        Button saveImage = new Button("Save Image");
        ColorPicker colorPicker = new ColorPicker();
        Label label = new Label("Stroke Width");
        Slider slider = new Slider(1, 10, 1);

        slider.showTickLabelsProperty().setValue(true);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText("Stroke Width: " + newValue.intValue());
            pencilDrawing.setStrokeWidth(newValue.doubleValue());
        });
        colorPicker.setOnAction(e -> pencilDrawing.setStroke(colorPicker.getValue()));
        clearButton.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        saveImage.setOnAction(e -> pencilDrawing.DownloadImage(canvas ,primaryStage));

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 800, 800);
        // set button in different StackPane
        VBox vbox = new VBox();
        VBox sliderLabel = new VBox();
        ButtonBar buttonBar = new ButtonBar();
        //padding top
        buttonBar.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        buttonBar.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(Color.BLANCHEDALMOND, null, null)));


//        vbox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        sliderLabel.getChildren().addAll(label, slider);
        buttonBar.getButtons().addAll(clearButton, colorPicker, sliderLabel, saveImage);
        vbox.getChildren().add(buttonBar);
        layout.getChildren().add(vbox);


        layout.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}