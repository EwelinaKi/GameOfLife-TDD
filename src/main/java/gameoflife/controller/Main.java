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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // number of cells in row/column
        int size = 30;
        // array of all CELLS in game
        Cell[] cellsList = createCellsList(size);

        stage.setTitle("Game of life");
        Pane root = new Pane();
        DisplayCells display = new DisplayCells(cellsList);
        new GameScene(root, stage, cellsList, display);
        display.initializeView(root, size);
    }

    public static Cell[] createCellsList(int size) {
        return IntStream
                .range(0, size * size)
                .mapToObj(index -> new Cell(index, size))
                .collect(Collectors.toList())
                .toArray(new Cell[size * size]);
    }
}