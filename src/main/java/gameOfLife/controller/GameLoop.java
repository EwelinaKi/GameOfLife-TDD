package main.java.gameOfLife.controller;

import main.java.gameOfLife.model.Cell;
import main.java.gameOfLife.view.DisplayCells;

public class GameLoop {

    public static void run() {
        calculateAllNewStatesForArray();
        setAllNewStatesForCells();
        DisplayCells.updateView();
    }

    private static void calculateAllNewStatesForArray() {
        for (Cell cell : Main.cells){
            cell.calculateNewState();
        }
    }

    private static void setAllNewStatesForCells() {
        for (Cell cell : Main.cells){
            cell.updateState();
        }
    }

}