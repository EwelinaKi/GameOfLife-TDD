package main.java.gameOfLife.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameScene extends Scene {


    public GameScene(Parent root, Stage stage) {
        super(root, 1400, 1000);
        getStylesheets().add(GameScene.class.getResource("/main/java/gameOfLife/view/app.css").toExternalForm());
        stage.setScene(this);
        stage.show();
    }
}
