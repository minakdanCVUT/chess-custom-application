package org.example.chess_application.software.service.start_config;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.chess_application.software.configuration.ChessFigureColor;
import org.example.chess_application.software.configuration.ChessPosition;
import org.example.chess_application.software.domain.ChessMap;
import org.example.chess_application.software.domain.figures.*;


public class StartMapConfiguration {
    public static void configureMap(ChessMap map){
        Gson gson = new Gson();
        try(
                FileReader position_reader = new FileReader("codespace/src/service/start_config/start_config.json");
                FileReader figure_reader = new FileReader("codespace/src/service/start_config/chess_figures.json")
        ) {
            JsonObject position_root = gson.fromJson(position_reader, JsonObject.class);
            JsonObject figure_root = gson.fromJson(figure_reader, JsonObject.class);

            configureColor(map, position_root, figure_root,"black");
            configureColor(map, position_root, figure_root,"white");

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    static private void configureColor(ChessMap map, JsonObject position_root,
                                       JsonObject figure_root, String color){
        JsonObject positionColorObject = position_root.getAsJsonObject(color);
        JsonObject figureColorObject = figure_root.getAsJsonObject(color);

        ChessFigureColor figureColor = switch (color){
            case("black") -> ChessFigureColor.BLACK;
            case("white") -> ChessFigureColor.WHITE;
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };

        configureKing(map, positionColorObject.getAsJsonObject("king"),
                figureColorObject.get("king"), figureColor);
        configureQueen(map, positionColorObject.getAsJsonObject("queen"),
                figureColorObject.get("queen"), figureColor);
        configureRock(map, positionColorObject.getAsJsonArray("rock"),
                figureColorObject.get("rock"), figureColor);
        configureBishop(map, positionColorObject.getAsJsonArray("bishop"),
                figureColorObject.get("bishop"), figureColor);
        configureKnight(map, positionColorObject.getAsJsonArray("knight"),
                figureColorObject.get("knight"), figureColor);
        configurePawn(map, positionColorObject.getAsJsonArray("pawn"),
                figureColorObject.get("pawn"), figureColor);
    }


    private static void configureKing(ChessMap map,
                                      JsonObject position_king,
                                      JsonElement figure_king,
                                      ChessFigureColor color){
        int row = position_king.get("r").getAsInt();
        int column = position_king.get("c").getAsInt();

        ChessFigureKing King = new ChessFigureKing(color, figure_king.getAsString(), new ChessPosition(row, column));
        map.setFigure(row, column, King);
    }

    private static void configureQueen(ChessMap map,
                                       JsonObject position_queen,
                                       JsonElement figure_queen,
                                       ChessFigureColor color) {
        int row = position_queen.get("r").getAsInt();
        int column = position_queen.get("c").getAsInt();

        ChessFigureQueen Queen = new ChessFigureQueen(color, figure_queen.getAsString(), new ChessPosition(row, column));
        map.setFigure(row, column, Queen);
    }

    private static void configureRock(ChessMap map,
                                      JsonArray position_rock,
                                      JsonElement figure_rock,
                                      ChessFigureColor color){
        for(JsonElement element : position_rock) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigureRock Rock = new ChessFigureRock(color, figure_rock.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Rock);
        }
    }

    private static void configureBishop(ChessMap map,
                                        JsonArray position_bishop,
                                        JsonElement figure_bishop,
                                        ChessFigureColor color){
        for(JsonElement element : position_bishop) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigureBishop Bishop = new ChessFigureBishop(color, figure_bishop.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Bishop);
        }
    }

    private static void configureKnight(ChessMap map,
                                        JsonArray position_knight,
                                        JsonElement figure_knight,
                                        ChessFigureColor color){
        for(JsonElement element : position_knight) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigureKnight Knight = new ChessFigureKnight(color, figure_knight.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Knight);
        }
    }

    private static void configurePawn(ChessMap map,
                                      JsonArray position_pawn,
                                      JsonElement figure_pawn,
                                      ChessFigureColor color) {
        for(JsonElement element : position_pawn) {
            JsonObject pair = element.getAsJsonObject();
            int row = pair.get("r").getAsInt();
            int column = pair.get("c").getAsInt();

            ChessFigurePawn Pawn = new ChessFigurePawn(color, figure_pawn.getAsString(), new ChessPosition(row, column));
            map.setFigure(row, column, Pawn);
        }
    }
}
