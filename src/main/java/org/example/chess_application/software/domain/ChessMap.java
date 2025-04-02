package org.example.chess_application.software.domain;

import org.example.chess_application.software.configuration.ChessPosition;
import org.example.chess_application.software.configuration.ChessCellColor;
import org.example.chess_application.software.service.start_config.StartMapConfiguration;
import org.example.chess_application.software.domain.ChessCell;

import java.util.*;

public class ChessMap {
    private final Map<Integer, Map<Integer, ChessCell>> map;

    public ChessMap() {
        map = new HashMap<>();
        boolean black = false;
        for(int i = 1; i <= 8; ++i){
            black = i % 2 == 1;
            map.put(i, new HashMap<>());
            for(int j = 1; j <= 8; ++j){
                if(black){
                    if(j % 2 == 1){
                        map.get(i)
                                .put(j, new ChessCell(new ChessPosition(i, j), ChessCellColor.BLACK));
                    }else{
                        map.get(i)
                                .put(j, new ChessCell(new ChessPosition(i, j), ChessCellColor.WHITE));
                    }
                }else{
                    if(j % 2 == 1){
                        map.get(i)
                                .put(j, new ChessCell(new ChessPosition(i, j), ChessCellColor.WHITE));
                    }else{
                        map.get(i)
                                .put(j, new ChessCell(new ChessPosition(i, j), ChessCellColor.BLACK));
                    }
                }
            }
        }
    }

    public void setFigure(int row, int column, ChessFigure figure){
        ChessCell cell = map.get(row).get(column);
        cell.setFigure(figure);
    }

    public void showMap(){
        for(int i = 1; i <= 8; ++i){
            for(int j = 1; j <= 8; ++j){
                ChessCell cell = map.get(i).get(j);
                if(cell.getFigure() == null){
                    if(cell.getColor() == ChessCellColor.WHITE){
                        if(j == 8)
                            System.out.print("■");
                        else
                            System.out.print("■  ");
                    }
                    else {
                        if (j == 8)
                            System.out.print("□");
                        else
                            System.out.print("□  ");
                    }
                }else{
                    if (j == 8)
                        System.out.print(cell.getFigure().getFigure());
                    else
                        System.out.print(cell.getFigure().getFigure() + "  ");
                }
            }
            System.out.println();
        }
    }

    public void showMapReverse(){
        for(int i = 8; i >= 1; --i){
            for(int j = 8; j >= 1; --j){
                ChessCell cell = map.get(i).get(j);
                if(cell.getFigure() == null){
                    if(cell.getColor() == ChessCellColor.WHITE){
                        if(j == 1)
                            System.out.print("■");
                        else
                            System.out.print("■  ");
                    }
                    else {
                        if (j == 1)
                            System.out.print("□");
                        else
                            System.out.print("□  ");
                    }
                }else{
                    if (j == 1)
                        System.out.print(cell.getFigure().getFigure());
                    else
                        System.out.print(cell.getFigure().getFigure() + "  ");
                }
            }
            System.out.println();
        }
    }

    public boolean checkToMove(ChessPosition pos){
        ChessCell cell = map.get(pos.getRow()).get(pos.getColumn());
        return cell.getFigure() == null;
    }

    public void moveFigure(ChessFigure figure, ChessPosition pos){
        ChessCell oldCell = map.get(figure.getPosition().getRow()).get(figure.getPosition().getColumn());
        oldCell.setFigure(null);
        figure.setPosition(pos);
        ChessCell newCell = map.get(pos.getRow()).get(pos.getColumn());
        newCell.setFigure(figure);
    }

    public ChessFigure getFigure(ChessPosition position){
        return map.get(position.getRow()).get(position.getColumn()).getFigure();
    }
}
