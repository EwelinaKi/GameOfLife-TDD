package main.java.gameOfLife.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.java.gameOfLife.controller.Main;


import java.util.ArrayList;

public abstract class DisplayCells {

    private static Image whiteCell = new Image("/main/resources/white25.png");
    private static Image blackCell = new Image("/main/resources/black25.png");
    private static ArrayList<ImageView> imagesToDisplay = new ArrayList<>();
    private static int length = Main.cells.length;
    private static int imageWidth = 25;


    public static void initializeView(Pane root, int columns) {
        setImages();
        setCoordinates(columns);
        addToPane(root);
    }

    public static void updateView() {
        updateImages();
    }

    private static void setImages() {
        for (int i = 0; i < length; i++) {
            ImageView cellImage = new ImageView();
            cellImage.setImage(blackCell);

            imagesToDisplay.add(cellImage);
        }
    }

    private static void setCoordinates(int columns) {
        int colNumb = 1;
        double x = 10;
        double y = 100;

        for (ImageView image : imagesToDisplay) {
            image.setX(x + imageWidth);
            image.setY(y);
            if (colNumb == columns) {
                x = 10;
                y = y + imageWidth;
                colNumb =1;
            } else {
                x = x + imageWidth;
                colNumb++;
            }
        }
    }

    private static void addToPane(Pane root) {
        for (ImageView image : imagesToDisplay) {
            root.getChildren().add(image);
        }
    }

    private static void updateImages() {
        for (int i = 0; i < length; i++) {
            if (Main.cells[i].isAlive()) {
                imagesToDisplay.get(i).setImage(whiteCell);
            } else {
                imagesToDisplay.get(i).setImage(blackCell);
            }
        }
    }
}
