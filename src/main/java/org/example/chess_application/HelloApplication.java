package org.example.chess_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.chess_application.UI.controllers.BoardController;
import org.example.chess_application.software.domain.ChessMap;

import java.io.IOException;

public class HelloApplication extends Application {

    private ChessMap map;

    public ChessMap getMap(){
        return map;
    }

    @Override
    public void start(Stage stage) throws IOException {
        map = new ChessMap();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("second_try.fxml"));
        Scene scene = new Scene(loader.load(), 800, 800);
        BoardController controller = loader.getController();
        controller.initializeData(this);
        stage.setTitle("Title");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}