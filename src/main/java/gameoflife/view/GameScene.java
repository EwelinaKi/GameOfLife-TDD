package gameoflife.view;

import gameoflife.controller.Cycle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameScene extends Scene {

    private ScheduledExecutorService execService = null;
    private int speed = 1000;
    private static Text cycleText = new Text(37, 40, "Cycles: ");

    public GameScene(Pane root, Stage stage) {
        super(root, 820, 1000);


        getStylesheets().add(GameScene.class.getResource("/app.css").toExternalForm());
        stage.setScene(this);

        Text gameName = new Text(510, 67,"Game Of Life");
        gameName.setId("gameName");
        Text speedText = new Text(200, 87,"Speed");
        speedText.setId("text");
        cycleText.setId("text");


        Button playBtn = new Button("Play");
        playBtn.setLayoutX(25);
        playBtn.setLayoutY(50);
        Button speedBtn = new Button("⟰");
        speedBtn.setLayoutX(250);
        speedBtn.setLayoutY(50);
        Button slowBtn = new Button("⟱");
        slowBtn.setLayoutX(160);
        slowBtn.setLayoutY(50);

        playBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                manageExecService(playBtn);
            }
        });

        speedBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                speed = speed > 100 ? speed - 100 : 100;
                updateSpeed();
            }
        });

        slowBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                speed = speed < 2000 ? speed + 100 : 2000;
                updateSpeed();
            }
        });

        root.getChildren().addAll(playBtn,slowBtn,speedBtn, gameName, speedText, cycleText);
        stage.show();
    }

    public static void cycleUpdate(Long count) {
        cycleText.setText("Cycles: " + Long.toString(count));
    }

    private void manageExecService(Button playBtn) {
        if (execService == null) {
            execService = Executors.newScheduledThreadPool(5);
            execService.scheduleAtFixedRate(() -> Cycle.run(), 0, speed, TimeUnit.MILLISECONDS);
            playBtn.setText("Pause");
        } else {
            execService.shutdown();
            execService = null;
            playBtn.setText("Play");
        }
    }

    private void updateSpeed() {
        if (!execService.equals(null)) {
            execService.shutdown();
            execService = null;
            execService = Executors.newScheduledThreadPool(5);
            execService.scheduleAtFixedRate(() -> Cycle.run(), 0, speed, TimeUnit.MILLISECONDS);
        }
    }
}
