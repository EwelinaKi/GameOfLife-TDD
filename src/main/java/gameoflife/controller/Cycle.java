package gameoflife.controller;

import gameoflife.model.Cell;
import gameoflife.view.DisplayCells;
import gameoflife.view.GameScene;

public class Cycle {

    public static Long cycleCount = Long.valueOf(0);

    public static void run() {
        calculateAllNewStatesForArray();
        setAllNewStatesForCells();
        DisplayCells.updateView();
        cycleCount = cycleCount +1;
        GameScene.cycleUpdate(cycleCount);
    }

    private static void calculateAllNewStatesForArray() {
        for (Cell cell : Main.CELLS){
            cell.calculateNewState();
        }
    }

    private static void setAllNewStatesForCells() {
        for (Cell cell : Main.CELLS){
            cell.updateState();
        }
    }
}