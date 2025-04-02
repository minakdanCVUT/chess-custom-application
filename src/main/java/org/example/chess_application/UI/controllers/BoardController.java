package org.example.chess_application.UI.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.example.chess_application.HelloApplication;
import org.example.chess_application.software.start_config.StartConfiguration;


public class BoardController {
    private HelloApplication application;

    @FXML
    private Pane boardPane;


    public void initializeData(HelloApplication application){
        this.application = application;
        ObservableList<Node> boxes = boardPane.getChildren();
        ObservableList<ObservableList<Node>> nodes = FXCollections.observableArrayList();
        for(int i = 1; i <= 8; ++i){
            int box_it = i - 1;
            HBox box = (HBox) boxes.get(box_it);
            boolean blackCell = i % 2 == 1;
            ObservableList<Node> buttons = box.getChildren();
            nodes.add(buttons);
            for(int j = 1; j <= 8; ++j){
                int button_it = j - 1;
                Button button = (Button) buttons.get(button_it);
                if(blackCell){
                    button.setStyle("-fx-background-color: #633200;");
                    blackCell = false;
                }else{
                    button.setStyle("-fx-background-color: #9a7b78;");
                    blackCell = true;
                }
            }
        }
        StartConfiguration.configureMap(application.getMap(), nodes);
    }

}
