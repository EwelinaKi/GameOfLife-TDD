package main.java.gameOfLife.controller;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.gameOfLife.model.Cell;
import main.java.gameOfLife.view.GameScene;

public class Main extends Application {

    //size of the game field
    public static final int ROWS_Y = 100;
    public static final int COLUMNS_X = 100;
    public static Cell[] cells = createCellsList(ROWS_Y, COLUMNS_X);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game of life");
        StackPane root = new StackPane();
        GameScene scene = new GameScene(root, stage);
    }

    public static Cell[] createCellsList(int rows, int columns) {
        Cell[] cells = new Cell[rows * columns];
        //TODO metoda umieszczająca obiekty cells w tablicy
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