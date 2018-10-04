package gameoflife.controller;

import gameoflife.model.Cell;
import gameoflife.view.DisplayCells;
import gameoflife.view.GameScene;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends Application {

    // array of all CELLS in game
    public static Cell[] CELLS;
    private static final int ROWS_Y = 30;
    private static final int COLUMNS_X = 30;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game of life");
        Pane root = new Pane();
        GameScene scene = new GameScene(root, stage);

        CELLS = createCellsList(ROWS_Y, COLUMNS_X);
        DisplayCells.initializeView(root, COLUMNS_X);
    }

    public static Cell[] createCellsList(int rows, int columns) {
        return IntStream
                .range(0, rows * columns)
                .mapToObj(index -> new Cell(index, rows, columns))
                .collect(Collectors.toList())
                .toArray(new Cell[rows * columns]);
    }
}