package gameoflife.view;

import gameoflife.controller.Cycle;
import gameoflife.model.Cell;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameScene extends Scene {

    private Text cycleText = createText(37, 40, "Cycles: ", "text");
    private int speed = 1000;
    private ScheduledExecutorService execService = null;

    public GameScene(Pane root, Stage stage, Cell[] cellsList, DisplayCells display) {
        super(root, 820, 1000);
        Cycle cycle = new Cycle(this, cellsList, display);

        getStylesheets().add(GameScene.class.getResource("/app.css").toExternalForm());
        stage.setScene(this);

        Text gameName = createText(510, 67, "Game Of Life", "gameName");
        Text speedText = createText(200, 87, "Speed", "text");

        Button playBtn = createButton(25, "Play");
        Button speedBtn = createButton(250, "⟰");
        Button slowBtn = createButton(160, "⟱");

        root.getChildren().addAll(playBtn, slowBtn, speedBtn, gameName, speedText, cycleText);
        stage.show();

        // buttons event handler
        playBtn.setOnAction(event -> manageExecService(playBtn, cycle));

        speedBtn.setOnAction(event -> {
            speed = speed > 100 ? speed - 100 : 100;
            updateSpeed(cycle);
        });

        slowBtn.setOnAction(event -> {
            speed = speed < 2000 ? speed + 100 : 2000;
            updateSpeed(cycle);
        });
    }

    public void cycleUpdate(Long count) {
        cycleText.setText("Cycles: " + Long.toString(count));
    }

    private void manageExecService(Button playBtn, Cycle cycle) {
        if (execService == null) {
            execService = Executors.newScheduledThreadPool(5);
            execService.scheduleAtFixedRate(cycle::run, 0, speed, TimeUnit.MILLISECONDS);
            playBtn.setText("Pause");
        } else {
            execService.shutdown();
            execService = null;
            playBtn.setText("Play");
        }
    }

    private void updateSpeed(Cycle cycle) {
        execService.shutdown();
        execService = null;
        execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(cycle::run, 0, speed, TimeUnit.MILLISECONDS);
    }

    private Button createButton(int posX, String text) {
        Button button = new Button(text);
        button.setLayoutX(posX);
        button.setLayoutY(50);
        return button;
    }

    private Text createText(int posX, int posY, String content, String className) {
        Text newText = new Text(posX, posY, content);
        newText.setId(className);
        return newText;
    }
}
