package gameoflife.view;

import gameoflife.model.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class DisplayCells {

    private final Image WHITE_CELL = new Image("/white50.png");
    private final Image BLACK_CELL = new Image("/black50.png");
    private final List<ImageView> imagesToDisplay;
    private final int imageWidth = 25;
    private final Cell[] cells;

    public DisplayCells(Cell[] cells) {
        this.cells = cells;
        this.imagesToDisplay = setImages();
    }

    public void initializeView(Pane root, int size) {
        setCoordinates(size);
        addToPane(root);
    }

    public void updateView() {
        updateImages();
    }

    private List<ImageView> setImages() {
        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            ImageView cellImage = new ImageView();
            cellImage.setImage(BLACK_CELL);
            int finalI = i;
            cellImage.setOnMouseClicked(e -> {
                if (cells[finalI].isAlive()) {
                    cells[finalI].kill();
                    cellImage.setImage(BLACK_CELL);
                } else {
                    cells[finalI].revive();
                    cellImage.setImage(WHITE_CELL);
                }
            });
            list.add(cellImage);
        }
        return list;
    }

    private void setCoordinates(int size) {
        ImageCoordinates coordinates = new ImageCoordinates(imageWidth);

        imagesToDisplay.forEach(image -> {
            image.setX(coordinates.getX() + imageWidth);
            image.setY(coordinates.getY());

            if (isEndOfColumn(size, coordinates.getColNumb())) {
                coordinates.addNewRow();
            } else {
                coordinates.addNewColumn();
            }
        });
    }

    private boolean isEndOfColumn(int columns, int colNumb) {
        return colNumb == columns;
    }

    private void addToPane(Pane root) {
        imagesToDisplay.forEach(image -> root.getChildren().add(image));
    }

    private void updateImages() {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].isAlive()) {
                imagesToDisplay.get(i).setImage(WHITE_CELL);
            } else {
                imagesToDisplay.get(i).setImage(BLACK_CELL);
            }
        }
    }
}
