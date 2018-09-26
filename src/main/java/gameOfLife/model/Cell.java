package main.java.gameOfLife.model;

public class Cell {

    private boolean isLiving;
    private boolean willLive;
    private int[][] neighbors;

    Cell(int posX, int posY) {
        isLiving = false;
        willLive = false;
        neighbors = createNeighborsArray(posX, posY);
    }

    public void calculateNewState() {
        //TODO testy
        //TODO ustawic willLive na bazie sąsiadów
    }

    public void setNewState() {
        isLiving = willLive;
    }

    public boolean getIsLiving() {
        return isLiving;
    }

    public boolean getWillLive() {
        return willLive;
    }

    private int[][] createNeighborsArray(int posX, int posY) {
        //TODO testy
        //TODO obliczyc i zapisac sasiadow komorki
        int neighbours[][] = null;
        return neighbours;
    }
}
