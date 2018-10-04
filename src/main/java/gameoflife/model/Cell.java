package gameoflife.model;

import gameoflife.controller.Main;
import java.util.stream.IntStream;

public class Cell {

    private final int[] neighbors;
    private boolean alive;
    private boolean goingToLive;

    public Cell(int index, int rows, int columns) {
        alive = false;
        goingToLive = false;
        neighbors = createNeighborsArray(index, rows, columns);
    }

    public void calculateNewState() {
        long livingNeighbors = IntStream
                .of(neighbors)
                .filter(neighbourIndex -> Main.CELLS[neighbourIndex].isAlive())
                .count();

        if (livingNeighbors < 2 || livingNeighbors > 3) {
            goingToLive = false;
        } else if (livingNeighbors == 3) {
            goingToLive = true;
        } else {
            goingToLive = alive;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isGoingToLive() {
        return goingToLive;
    }

    public int[] getNeighbors() {
        return neighbors;
    }

    public void updateState() {
        alive = goingToLive;
    }

    public void kill() {
        alive = false;
    }

    public void revive() {
        alive = true;
    }

    private int[] createNeighborsArray(int index, int rows, int columns) {

        if (isOnCorner(index, rows, columns)) {
            return calculateNeighborsForCorner(index, rows, columns);
        } else if (isOnUpperEdge(index, columns)) {
            return calculateNeighborsForUpperEdge(index, columns);
        } else if (isOnBottomEdge(index, rows, columns)) {
            return calculateNeighborsForBottomEdge(index, columns);
        } else if (isOnLeftEdge(index, rows)) {
            return calculateNeighborsForLeftEdge(index, columns);
        } else if (isOnRightEdge(index, columns)) {
            return calculateNeighborsForRightEdge(index, columns);
        } else {
            return calculateNeighborsForMiddle(index, columns);
        }
    }

    private boolean isOnUpperEdge(int index, int columns) {
        return index <= columns - 1;
    }

    private boolean isOnBottomEdge(int index, int rows, int columns) {
        return index >= rows * columns - columns;
    }

    private boolean isOnLeftEdge(int index, int rows) {
        return index % rows == 0;
    }

    private boolean isOnRightEdge(int index, int columns) {
        return (index + 1) % columns == 0;
    }

    private boolean isOnCorner(int index, int rows, int columns) {
        return index == 0 || index == (columns - 1) || index == columns * (rows - 1) || index == ((rows * columns) - 1);
    }

    private int[] calculateNeighborsForCorner(int index, int rows, int columns) {
        if (index == 0) {
            // upper left
            return new int[]{
                    index + 1,
                    index + columns,
                    index + columns + 1};
        } else if (index == columns - 1) {
            // upper right
            return new int[]{
                    index - 1,
                    index + columns - 1,
                    index + columns};
        } else if (index == columns * (rows - 1)) {
            // bottom left
            return new int[]{
                    index - columns,
                    index - columns + 1,
                    index + 1};
        } else {
            // bottom right
            return new int[]{
                    index - columns - 1,
                    index - columns,
                    index - 1};
        }
    }

    private int[] calculateNeighborsForUpperEdge(int index, int columns) {
        return new int[]{
                index - 1,
                index + 1,
                index + columns - 1,
                index + columns,
                index + columns + 1
        };
    }

    private int[] calculateNeighborsForBottomEdge(int index, int columns) {
        return new int[] {
                index - columns - 1,
                index - columns,
                index - columns + 1,
                index - 1,
                index + 1
        };
    }

    private int[] calculateNeighborsForLeftEdge(int index, int columns) {
        return new int[] {
                index - columns,
                index - columns + 1,
                index + 1,
                index + columns,
                index + columns + 1
        };
    }

    private int[] calculateNeighborsForRightEdge(int index, int columns) {
        return new int[] {
                index - columns - 1,
                index - columns,
                index - 1,
                index + columns - 1,
                index + columns
        };
    }

    private int[] calculateNeighborsForMiddle(int index, int columns) {
        return new int[] {
                index - columns - 1,
                index - columns,
                index - columns + 1,
                index - 1,
                index + 1,
                index + columns - 1,
                index + columns,
                index + columns + 1
        };
    }

}
