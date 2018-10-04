package gameoflife.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


import static gameoflife.controller.Main.CELLS;

public class DisplayCells {

    private static Image WHITE_CELL = new Image("/white50.png");
    private static Image BLACK_CELL = new Image("/black50.png");
    private static List<ImageView> IMAGES_TO_DISPLAY = setImages();
    private static int IMAGE_WIDTH = 25;


    public static void initializeView(Pane root, int size) {
        setCoordinates(size);
        addToPane(root);
    }

    public static void updateView() {
        updateImages();
    }

    private static List<ImageView> setImages() {
        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < CELLS.length; i++) {
            ImageView cellImage = new ImageView();
            cellImage.setImage(BLACK_CELL);
            int finalI = i;
            cellImage.setOnMouseClicked(e -> {
                if (CELLS[finalI].isAlive()) {
                    CELLS[finalI].kill();
                    cellImage.setImage(BLACK_CELL);
                } else {
                    CELLS[finalI].revive();
                    cellImage.setImage(WHITE_CELL);
                }
            });
            list.add(cellImage);
        }
        return list;
    }

    private static void setCoordinates(int size) {
        ImageCoordinates coordinates = new ImageCoordinates();

        IMAGES_TO_DISPLAY.forEach(image -> {
            image.setX(coordinates.getX() + IMAGE_WIDTH);
            image.setY(coordinates.getY());

            if (isEndOfColumn(size, coordinates.getColNumb())) {
                coordinates.addNewRow(IMAGE_WIDTH);
            } else {
                coordinates.addNewColumn(IMAGE_WIDTH);
            }
        });
    }

    private static boolean isEndOfColumn(int columns, int colNumb) {
        return colNumb == columns;
    }

    private static void addToPane(Pane root) {
        IMAGES_TO_DISPLAY.forEach(image -> root.getChildren().add(image));
    }

    private static void updateImages() {
        for (int i = 0; i < CELLS.length; i++) {
            if (CELLS[i].isAlive()) {
                IMAGES_TO_DISPLAY.get(i).setImage(WHITE_CELL);
            } else {
                IMAGES_TO_DISPLAY.get(i).setImage(BLACK_CELL);
            }
        }
    }
}
