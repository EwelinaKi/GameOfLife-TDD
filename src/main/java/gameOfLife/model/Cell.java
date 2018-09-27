package main.java.gameOfLife.model;

public class Cell {

    private final int[] neighbors;
    private boolean alive;
    private boolean goingToLive;

    public Cell(int index) {
        alive = false;
        goingToLive = false;
        neighbors = createNeighborsArray(index);
    }

    public void calculateNewState() {
        //TODO ustawic goingToLive na bazie sąsiadów
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

    private int[] createNeighborsArray(int index) {
        //TODO obliczyc i zapisac sasiadow komorki
        int neighbours[] = null;
        return neighbours;
    }
}
