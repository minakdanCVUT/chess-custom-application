package org.example.chess_application.UI.handlers;

import javafx.scene.control.Button;
import org.example.chess_application.software.configuration.JavaFXButtonStorage;
import org.example.chess_application.software.domain.ChessMap;

public class CellEventClicker {
    public static void handle(Button button, ChessMap map){
        JavaFXButtonStorage storage = (JavaFXButtonStorage) button.getUserData();

        if(!storage.isPressed()){// если кнопка не была нажата, то запоминаем, что она нажата и выполняем нужные инструкции

            FutureMovesHandler.handle(storage.getFigure().showMoves(map));
            //System.out.println("Фигура на позиции " + storage.getFigure().getPosition().getRow() + ":" + storage.getFigure().getPosition().getColumn() + " нажата");
            storage.setPressed(); // запоминаем состояние нажатия

        }else{ // если кнопка не нажата

            //System.out.println("Фигура на позиции " + storage.getFigure().getPosition().getRow() + ":" + storage.getFigure().getPosition().getColumn() + " отжата");
            storage.setUnpressed(); // запоминаем состояние отжатия кнопки

        }
    }
}
