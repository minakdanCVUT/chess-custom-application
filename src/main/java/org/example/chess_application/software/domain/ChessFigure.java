package org.example.chess_application.software.domain;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.example.chess_application.software.configuration.ChessFigureColor;
import org.example.chess_application.software.configuration.ChessPosition;

abstract public class ChessFigure {
    protected ChessPosition position;
    protected ChessFigureColor color;
    protected String figure;

    public ChessFigure(ChessFigureColor color, String figure, ChessPosition position) {
        this.color = color;
        this.figure = figure;
        this.position = position;
    }

    public ChessFigureColor getColor() {
        return color;
    }

    public void setColor(ChessFigureColor color) {
        this.color = color;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public ChessPosition getPosition() {
        return position;
    }

    public void setPosition(ChessPosition position) {
        this.position = position;
    }

    abstract public void move(ChessMap map) throws Exception;

    abstract public void beat(ChessMap map) throws Exception;

    abstract public ObservableList<Node> showMoves(ChessMap map);
}
