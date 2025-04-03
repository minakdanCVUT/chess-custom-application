package org.example.chess_application.software.configuration;

import org.example.chess_application.software.domain.ChessFigure;

public class JavaFXButtonStorage {
    private boolean isPressed;
    private ChessFigure figure;

    public static String disabledStyle = "-fx-opacity: 1.0;";

    public JavaFXButtonStorage() {
        isPressed = false;
        this.figure = null;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(){
        isPressed = true;
    }

    public void setUnpressed(){
        isPressed = false;
    }


    public ChessFigure getFigure() {
        return figure;
    }

    public void setFigure(ChessFigure figure) {
        this.figure = figure;
    }
}
