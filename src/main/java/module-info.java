module com.example.paint {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.example.paint to javafx.fxml;
    exports com.example.paint;
}