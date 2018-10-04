package gameoflife.view;


class ImageCoordinates {

    private int colNumb = 1;
    private int imageWidth;

    // coordinates of cells drawing start point
    private double x = 10;
    private double y = 100;

    ImageCoordinates(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    int getColNumb() {
        return colNumb;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    void addNewRow() {
        x = 10;
        y += this.imageWidth;
        colNumb = 1;
    }

    void addNewColumn() {
        x += this.imageWidth;
        colNumb++;
    }
}
