package org.example.chess_application.software.domain;

import org.example.chess_application.software.configuration.ChessPosition;
import org.example.chess_application.software.configuration.ChessCellColor;

public class ChessCell {
    private ChessPosition position;
    private ChessCellColor color;
    private ChessFigure figure;

    public ChessCell(ChessPosition position, ChessCellColor color) {
        this.position = position;
        this.color = color;
        figure = null;
    }

    public ChessPosition getPosition() {
        return position;
    }

    public void setPosition(ChessPosition position) {
        this.position = position;
    }

    public ChessCellColor getColor() {
        return color;
    }

    public void setColor(ChessCellColor color) {
        this.color = color;
    }

    public ChessFigure getFigure() {
        return figure;
    }

    public void setFigure(ChessFigure figure) {
        this.figure = figure;
    }
}
