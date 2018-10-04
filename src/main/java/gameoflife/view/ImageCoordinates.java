package gameoflife.view;

class ImageCoordinates {
    private int colNumb = 1;
    private double x = 10;
    private double y = 100;

    int getColNumb() {
        return colNumb;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    void addNewRow(double rowHeight) {
        x = 10;
        y += rowHeight;
        colNumb = 1;
    }

    void addNewColumn(int columnWidth) {
        x += columnWidth;
        colNumb++;
    }
}
