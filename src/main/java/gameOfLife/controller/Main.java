package main.java.gameOfLife.controller;

import javafx.scene.layout.Pane;
import main.java.gameOfLife.model.Cell;
import main.java.gameOfLife.view.DisplayCells;
import main.java.gameOfLife.view.GameScene;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    // array of all cells in game
    public static Cell[] cells;

    //size of the game field
    private static final int ROWS_Y = 30;
    private static final int COLUMNS_X = 53;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game of life");
        Pane root = new Pane();
        GameScene scene = new GameScene(root, stage);

        cells = createCellsList(ROWS_Y, COLUMNS_X);
        DisplayCells.initializeView(root, COLUMNS_X);
    }

    public static Cell[] createCellsList(int rows, int columns) {
        int size = rows * columns;
        Cell[] cells = new Cell[size];
        for (int index = 0; index < size; index++) {
            cells[index] = new Cell(index, rows, columns);
        }
        return cells;
    }
}


//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        root.getChildren().add(btn);