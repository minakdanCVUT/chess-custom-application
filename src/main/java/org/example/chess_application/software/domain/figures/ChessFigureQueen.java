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

public class ChessFigureQueen extends ChessFigure {
    public ChessFigureQueen(ChessFigureColor color, String figure, ChessPosition position) {
        super(color, figure, position);
    }
    private static class QueenMove{
        public int row;
        public int column;

        public QueenMove(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    private static final List<QueenMove> moves = new ArrayList<>(Arrays.asList(
            new QueenMove(1, 0),
            new QueenMove(-1, 0),
            new QueenMove(0, 1),
            new QueenMove(0, -1),
            new QueenMove(1, 1),
            new QueenMove(-1, -1),
            new QueenMove(1, -1),
            new QueenMove(-1, 1)
    ));

    @Override
    public void beat(ChessMap map) throws Exception{

    }

    @Override
    public ObservableList<Node> showMoves(ChessMap map) {
        ObservableList<Node> goodToMove = FXCollections.observableArrayList();
        for(QueenMove move: moves){
            int iteration = 1;
            while(true) {
                ChessPosition pos = new ChessPosition(position.getRow() + move.row * iteration, position.getColumn() + move.column * iteration);
                if (pos.getRow() >= 1 && pos.getRow() <= 8 && pos.getColumn() >= 1 && pos.getColumn() <= 8) {
                    if (map.checkToMove(pos)) {
                        goodToMove.add(map.getNode(pos.getRow(), pos.getColumn()));
                        iteration++;
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        return goodToMove;
    }
}
