package org.example.chess_application.software.start_config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.example.chess_application.software.configuration.ChessFigureColor;
import org.example.chess_application.software.configuration.ChessPosition;
import org.example.chess_application.software.domain.ChessMap;
import org.example.chess_application.software.domain.figures.*;

import java.io.FileReader;
import java.io.IOException;


public class StartConfiguration {
    public static void configureMap(ChessMap map, ObservableList<ObservableList<Node>> nodes){
        Gson gson = new Gson();
        try(
                FileReader position_reader = new FileReader("/Users/danilmac/IdeaProjects/chess_application/src/main/java/org/example/chess_application/software/start_config/start_config.json");
                FileReader figure_reader = new FileReader("/Users/danilmac/IdeaProjects/chess_application/src/main/java/org/example/chess_application/software/start_config/chess_figures.json")
        ) {
            JsonObject position_root = gson.fromJson(position_reader, JsonObject.class);
            JsonObject figure_root = gson.fromJson(figure_reader, JsonObject.class);

            configureColor(map, nodes, position_root, figure_root,"black");
            configureColor(map, nodes, position_root, figure_root,"white");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void configureColor(ChessMap map, ObservableList<ObservableList<Node>> nodes, JsonObject pos, JsonObject fig, String color){
        JsonObject positionColorObject = pos.getAsJsonObject(color);
        JsonObject figureColorObject = fig.getAsJsonObject(color);

        int fontSize = 30;

        ChessFigureColor figureColor = switch (color){
            case("black") -> ChessFigureColor.BLACK;
            case("white") -> ChessFigureColor.WHITE;
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };

        configureKing(map, nodes, positionColorObject.getAsJsonObject("king"),
                figureColorObject.get("king"), fontSize, figureColor);
        configureQueen(map, nodes, positionColorObject.getAsJsonObject("queen"),
                figureColorObject.get("queen"), fontSize, figureColor);
        configureRock(map, nodes, positionColorObject.getAsJsonArray("rock"),
                figureColorObject.get("rock"), fontSize, figureColor);
        configureBishop(map, nodes, positionColorObject.getAsJsonArray("bishop"),
                figureColorObject.get("bishop"), fontSize, figureColor);
        configureKnight(map, nodes, positionColorObject.getAsJsonArray("knight"),
                figureColorObject.get("knight"), fontSize, figureColor);
        configurePawn(map, nodes, positionColorObject.getAsJsonArray("pawn"),
                figureColorObject.get("pawn"), fontSize, figureColor);
    }

    private static void configureKing(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                      JsonObject position_king,
                                      JsonElement figure_king, int fontSize, ChessFigureColor color){
        int row = position_king.get("r").getAsInt();
        int column = position_king.get("c").getAsInt();


        ChessFigureKing King = new ChessFigureKing(color, figure_king.getAsString(), new ChessPosition(row, column));
        map.setFigure(row, column, King);

        Button button = (Button) nodes.get(row - 1).get(column - 1);
        button.setText(figure_king.getAsString());
        button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));

    }

    private static void configureQueen(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                       JsonObject position_queen,
                                       JsonElement figure_queen, int fontSize, ChessFigureColor color) {
        int row = position_queen.get("r").getAsInt();
        int column = position_queen.get("c").getAsInt();

        ChessFigureQueen Queen = new ChessFigureQueen(color, figure_queen.getAsString(), new ChessPosition(row, column));
        map.setFigure(row, column, Queen);

        Button button = (Button) nodes.get(row - 1).get(column - 1);
        button.setText(figure_queen.getAsString());
        button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));
    }

    private static void configureRock(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                      JsonArray position_rock,
                                      JsonElement figure_rock, int fontSize, ChessFigureColor color){
        for(JsonElement element : position_rock) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigureRock Rock = new ChessFigureRock(color, figure_rock.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Rock);

            Button button = (Button) nodes.get(row - 1).get(column - 1);
            button.setText(figure_rock.getAsString());
            button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));
        }
    }

    private static void configureBishop(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                        JsonArray position_bishop,
                                        JsonElement figure_bishop, int fontSize, ChessFigureColor color){
        for(JsonElement element : position_bishop) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigureBishop Bishop = new ChessFigureBishop(color, figure_bishop.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Bishop);

            Button button = (Button) nodes.get(row - 1).get(column - 1);
            button.setText(figure_bishop.getAsString());
            button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));
        }
    }

    private static void configureKnight(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                        JsonArray position_knight,
                                        JsonElement figure_knight, int fontSize, ChessFigureColor color){
        for(JsonElement element : position_knight) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigureKnight Knight = new ChessFigureKnight(color, figure_knight.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Knight);

            Button button = (Button) nodes.get(row - 1).get(column - 1);
            button.setText(figure_knight.getAsString());
            button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));
        }
    }

    private static void configurePawn(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                      JsonArray position_pawn,
                                      JsonElement figure_pawn, int fontSize, ChessFigureColor color) {
        for(JsonElement element : position_pawn) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigurePawn Pawn = new ChessFigurePawn(color, figure_pawn.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Pawn);

            Button button = (Button) nodes.get(row - 1).get(column - 1);
            button.setText(figure_pawn.getAsString());
            button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));
        }
    }
}
