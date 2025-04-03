package org.example.chess_application.software.domain.figures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.example.chess_application.software.configuration.ChessFigureColor;
import org.example.chess_application.software.configuration.ChessPosition;
import org.example.chess_application.software.domain.ChessFigure;
import org.example.chess_application.software.domain.ChessMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessFigurePawn extends ChessFigure {
    public ChessFigurePawn(ChessFigureColor color, String figure, ChessPosition position) {
        super(color, figure, position);
    }

    private static class PawnMove{
        public int row;
        public int column;

        public PawnMove(int row) {
            this.row = row;
            this.column = 0;
        }
    }

    private static final List<PawnMove> moves = new ArrayList<>(Arrays.asList(
            new PawnMove(1),
            new PawnMove(2)
    ));

    @Override
    public void move(ChessMap map) throws Exception{

    }

    @Override
    public void beat(ChessMap map) throws Exception{

    }

    @Override
    public ObservableList<Node> showMoves(ChessMap map) {
        int rowMove = 1;
        if(color == ChessFigureColor.BLACK){
            rowMove = -1;
        }
        ObservableList<Node> goodToMove = FXCollections.observableArrayList();
        for(PawnMove move: moves){
            ChessPosition pos = new ChessPosition(position.getRow() + move.row * rowMove, position.getColumn() + move.column);
            if(pos.getRow() >= 1 && pos.getRow() <= 8 && pos.getColumn() >= 1 && pos.getColumn() <= 8){
                if(map.checkToMove(pos)){
                    goodToMove.add(map.getNode(pos.getRow(), pos.getColumn()));
                }
            }
        }
        return goodToMove;
    }
}
