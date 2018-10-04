package gameoflife.model;

import java.util.stream.IntStream;


public class Cell {

    private final int[] neighbors;
    private boolean alive;
    private boolean goingToLive;

    public Cell(int index, int size) {

        alive = false;
        goingToLive = false;
        neighbors = createNeighborsArray(index, size);
    }

    public void calculateNewState(Cell[] cells) {
        long livingNeighbors = IntStream
                .of(neighbors)
                .filter(neighbourIndex -> cells[neighbourIndex].isAlive())
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

    public void kill() {
        alive = false;
    }

    public void revive() {
        alive = true;
    }

    public void updateState() {
        alive = goingToLive;
    }

    boolean isGoingToLive() {
        return goingToLive;
    }

    int[] getNeighbors() {
        return neighbors;
    }

    private int[] createNeighborsArray(int index, int size) {

        if (isOnCorner(index, size)) {
            return calculateNeighborsForCorner(index, size);
        } else if (isOnUpperEdge(index, size)) {
            return calculateNeighborsForUpperEdge(index, size);
        } else if (isOnBottomEdge(index, size)) {
            return calculateNeighborsForBottomEdge(index, size);
        } else if (isOnLeftEdge(index, size)) {
            return calculateNeighborsForLeftEdge(index, size);
        } else if (isOnRightEdge(index, size)) {
            return calculateNeighborsForRightEdge(index, size);
        } else {
            return calculateNeighborsForMiddle(index, size);
        }
    }

    private boolean isOnUpperEdge(int index, int size) {
        return index <= size - 1;
    }

    private boolean isOnBottomEdge(int index, int size) {
        return index >= size * size - size;
    }

    private boolean isOnLeftEdge(int index, int size) {
        return index % size == 0;
    }

    private boolean isOnRightEdge(int index, int size) {
        return (index + 1) % size == 0;
    }

    private boolean isOnCorner(int index, int size) {
        return index == 0 || index == (size - 1) || index == size * (size - 1) || index == ((size * size) - 1);
    }

    private int[] calculateNeighborsForCorner(int index, int size) {
        if (index == 0) {
            // upper left
            return new int[]{
                    index + 1,
                    index + size,
                    index + size + 1};
        } else if (index == size - 1) {
            // upper right
            return new int[]{
                    index - 1,
                    index + size - 1,
                    index + size};
        } else if (index == size * (size - 1)) {
            // bottom left
            return new int[]{
                    index - size,
                    index - size + 1,
                    index + 1};
        } else {
            // bottom right
            return new int[]{
                    index - size - 1,
                    index - size,
                    index - 1};
        }
    }

    private int[] calculateNeighborsForUpperEdge(int index, int size) {
        return new int[]{
                index - 1,
                index + 1,
                index + size - 1,
                index + size,
                index + size + 1
        };
    }

    private int[] calculateNeighborsForBottomEdge(int index, int size) {
        return new int[]{
                index - size - 1,
                index - size,
                index - size + 1,
                index - 1,
                index + 1
        };
    }

    private int[] calculateNeighborsForLeftEdge(int index, int size) {
        return new int[]{
                index - size,
                index - size + 1,
                index + 1,
                index + size,
                index + size + 1
        };
    }

    private int[] calculateNeighborsForRightEdge(int index, int size) {
        return new int[]{
                index - size - 1,
                index - size,
                index - 1,
                index + size - 1,
                index + size
        };
    }

    private int[] calculateNeighborsForMiddle(int index, int size) {
        return new int[]{
                index - size - 1,
                index - size,
                index - size + 1,
                index - 1,
                index + 1,
                index + size - 1,
                index + size,
                index + size + 1
        };
    }

}
