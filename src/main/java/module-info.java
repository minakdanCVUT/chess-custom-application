module org.example.chess_application {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;

    opens org.example.chess_application to javafx.fxml;
    exports org.example.chess_application;
    exports org.example.chess_application.UI.controllers;
    opens org.example.chess_application.UI.controllers to javafx.fxml;
    exports org.example.chess_application.software.start_config;
    opens org.example.chess_application.software.start_config to javafx.fxml;
}