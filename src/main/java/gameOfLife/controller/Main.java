package main.java.gameOfLife.controller;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.gameOfLife.view.GameScene;

import java.util.ArrayList;


public class Main extends Application {


    //size of the game field
    public static final int SIZE_X = 100;
    public static final int SIZE_Y = 100;
    public static ArrayList cells = new ArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game of life");
        StackPane root = new StackPane();
        GameScene scene = new GameScene(root, stage);
        GameLoop gameLoop = new GameLoop();
//        gameLoop.start();
//        gameLoop.stop();

    }

    public ArrayList createCellsList(int SIZE_X, int SIZE_Y) {
        ArrayList list = new ArrayList();
        //TODO metoda umieszczająca obiekty cells w liscie 2D

        return list;
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