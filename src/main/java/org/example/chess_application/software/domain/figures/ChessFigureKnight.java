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

public class ChessFigureKnight extends ChessFigure {
    public ChessFigureKnight(ChessFigureColor color, String figure, ChessPosition position) {
        super(color, figure, position);
    }

    private static class KnightMove{
        public int row;
        public int column;
        public KnightMove(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    private static final List<KnightMove> moves = new ArrayList<>(Arrays.asList(
            new KnightMove(2, -1),
            new KnightMove(2, 1),
            new KnightMove(-2, -1),
            new KnightMove(-2, 1),
            new KnightMove(1, 2),
            new KnightMove(-1, 2),
            new KnightMove(1, -2),
            new KnightMove(-1, -2)
    ));


    @Override
    public void beat(ChessMap map) throws Exception{

    }

    @Override
    public ObservableList<Node> showMoves(ChessMap map) {
        ObservableList<Node> goodToMove = FXCollections.observableArrayList();
        for(KnightMove move : moves){
            ChessPosition pos = new ChessPosition(position.getRow() + move.row, position.getColumn() + move.column);
            if(pos.getRow() >= 1 && pos.getRow() <= 8 && pos.getColumn() >= 1 && pos.getColumn() <= 8){
                if(map.checkToMove(pos)){
                    goodToMove.add(map.getNode(pos.getRow(), pos.getColumn()));
                }
            }
        }
        return goodToMove;
    }

}
