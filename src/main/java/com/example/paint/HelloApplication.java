package com.example.paint;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HelloApplication extends Application {
    Canvas canvas = new Canvas(400, 400);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Paint");
        gc.strokeRect(100, 100, 200, 200);
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();
    }
}