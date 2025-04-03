package org.example.chess_application.UI.handlers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class FutureMovesHandler {
    private static final String styleForMove = "-fx-background-color: green;";
    public static void handle(ObservableList<Node> buttonsToUnblock){
        if(!buttonsToUnblock.isEmpty()) {
            for (Node node : buttonsToUnblock) {
                Button button = (Button) node;
                button.setDisable(false);
                button.setStyle(styleForMove);
            }
        }
    }

    public static void notHandle(ObservableList<Node> buttonToUnblock){

    }
}
