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
import org.example.chess_application.software.configuration.JavaFXButtonStorage;
import org.example.chess_application.software.domain.ChessFigure;
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

    private static void setSettings(ObservableList<ObservableList<Node>> nodes, ChessMap map,
                                    int row, int column, ChessFigure figure,
                                    JsonElement icon, int fontSize){
        map.setFigure(row, column, figure);

        Button button = (Button) nodes.get(row - 1).get(column - 1);

        JavaFXButtonStorage storage = new JavaFXButtonStorage(figure);
        button.setUserData(storage);

        button.setText(icon.getAsString());
        button.setStyle(button.getStyle() + String.format(";-fx-font-size: %dpx;", fontSize));
    }

    private static void configureKing(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                      JsonObject position_king,
                                      JsonElement icon, int fontSize, ChessFigureColor color){
        int row = position_king.get("r").getAsInt();
        int column = position_king.get("c").getAsInt();

        ChessFigure figure = new ChessFigureKing(color, icon.getAsString(), new ChessPosition(row, column));
        setSettings(nodes, map, row, column, figure, icon, fontSize);

    }

    private static void configureQueen(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                       JsonObject position_queen,
                                       JsonElement icon, int fontSize, ChessFigureColor color) {
        int row = position_queen.get("r").getAsInt();
        int column = position_queen.get("c").getAsInt();

        ChessFigure figure = new ChessFigureQueen(color, icon.getAsString(), new ChessPosition(row, column));
        setSettings(nodes, map, row, column, figure, icon, fontSize);
    }

    private static void configureRock(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                      JsonArray position_rock,
                                      JsonElement icon, int fontSize, ChessFigureColor color){
        for(JsonElement element : position_rock) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigure figure = new ChessFigureRock(color, icon.getAsString(), new ChessPosition(row, column));
            setSettings(nodes, map, row, column, figure, icon, fontSize);
        }
    }

    private static void configureBishop(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                        JsonArray position_bishop,
                                        JsonElement icon, int fontSize, ChessFigureColor color){
        for(JsonElement element : position_bishop) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigure figure = new ChessFigureBishop(color, icon.getAsString(), new ChessPosition(row, column));
            setSettings(nodes, map, row, column, figure, icon, fontSize);
        }
    }

    private static void configureKnight(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                        JsonArray position_knight,
                                        JsonElement icon, int fontSize, ChessFigureColor color){
        for(JsonElement element : position_knight) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigure figure = new ChessFigureKnight(color, icon.getAsString(), new ChessPosition(row, column));
            setSettings(nodes, map, row, column, figure, icon, fontSize);
        }
    }

    private static void configurePawn(ChessMap map, ObservableList<ObservableList<Node>> nodes,
                                      JsonArray position_pawn,
                                      JsonElement icon, int fontSize, ChessFigureColor color) {
        for(JsonElement element : position_pawn) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigure figure = new ChessFigurePawn(color, icon.getAsString(), new ChessPosition(row, column));
            setSettings(nodes, map, row, column, figure, icon, fontSize);
        }
    }
}
