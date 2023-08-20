package com.example.paint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class PencilDrawingController extends Canvas {
    GraphicsContext gc;

    PencilDrawingController(GraphicsContext gc, Canvas canvas) {
        this.gc = gc;
        canvas.setWidth(700);
        canvas.setHeight(650);
        Start(canvas);
    }

    public void Start(Canvas canvas) {
        DrawOnMousePressed(canvas);
        DrawOnMouseDragged(canvas);
        DrawOnMouseReleased(canvas);
    }

    public void setStroke(Paint color) {
        gc.setStroke(color);
    }
    public void setStrokeWidth(double width) {
        gc.setLineWidth(width);
    }

    private void DrawOnMousePressed(Canvas canvas) {
        canvas.setOnMousePressed(e -> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });
    }

    private void DrawOnMouseDragged(Canvas canvas) {
        canvas.setOnMouseDragged(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });
    }

    private void DrawOnMouseReleased(Canvas canvas) {
        canvas.setOnMouseReleased(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
            gc.closePath();
        });
    }

    public void DownloadImage(Canvas canvas, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        fileChooser.setInitialFileName("image.png");

        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error!");
            }
        }




    }
}
