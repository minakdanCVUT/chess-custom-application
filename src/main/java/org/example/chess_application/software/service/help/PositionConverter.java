package org.example.chess_application.software.service.help;

import org.example.chess_application.software.configuration.ChessPosition;

public class PositionConverter {
    public static ChessPosition convert(String position){
        int letter = (position.charAt(0) - 'a') + 1;
        int number = 8 - (position.charAt(1) - '0') + 1;
        return new ChessPosition(letter, number);
    }

    public static String convertBack(ChessPosition position){
        String letter = Character.toString((char) ('a' + (position.getRow() - 1)));
        int number = (position.getColumn() - 8 - 1) * (-1);
        return String.format("%s%d", letter, number);
    }
}
