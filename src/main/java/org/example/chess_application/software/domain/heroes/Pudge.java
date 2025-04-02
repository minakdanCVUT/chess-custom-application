package org.example.chess_application.software.domain.heroes;

import org.example.chess_application.software.configuration.ChessFigureColor;
import org.example.chess_application.software.configuration.ChessPosition;
import org.example.chess_application.software.domain.ChessFigure;
import org.example.chess_application.software.domain.ChessMap;
import org.example.chess_application.software.domain.ChessFigureHero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pudge extends ChessFigure implements ChessFigureHero {
    public Pudge(ChessFigureColor color, String figure, ChessPosition position) {
        super(color, figure, position);
    }

    @Override
    public void move(ChessMap map) throws Exception {
        int rowMove = 1;
        int colMove = 1;
        if (color == ChessFigureColor.BLACK) {
            rowMove = -1;
            colMove = -1;
        }
        ChessPosition newPosition = new ChessPosition(position.getRow() + 1, position.getColumn() +1);
        if(map.checkToMove(newPosition)){
            map.moveFigure(this, newPosition);
        }else{
            throw new Exception("Вы не можете сходить этим Пуджом");
        }
    }

    @Override
    public void beat(ChessMap map) throws Exception {

    }

    @Override
    public void showMoves(ChessMap map) throws Exception {

    }


    //тут короче метод для хука, идея такая: чтоб мог хукать и свои фигуры и чужие.
    //если хукает свою, то она ставится перед ним (в направлении в котором хукал)
    //если чужую, то думаю как лучше сделать (1 вариант, что он ее срубает(но на ее место не встает), 2 вариант, что так же ставит перед собой)
    //можно вообще сделать в соотвествии с дотой (крипов-пешки убивает крюком, героев-фигуры к себе подтягивает), но это надо будет уже разбираться, когнда баланс будем править
    @Override
    public void skill(ChessMap map) throws Exception {
        int rowHook = 3;
        int colHook = 3;
        if (color == ChessFigureColor.BLACK) {
            rowHook = -3;
            colHook = -3;
        }

    }

    private static class PudgeMoves{
        public int cntCells;
        public PudgeMoves(int cntCells){ this.cntCells = cntCells; }
    }

    static List<PudgeMoves> moves = new ArrayList<PudgeMoves>(Arrays.asList(new PudgeMoves(1)));

}
