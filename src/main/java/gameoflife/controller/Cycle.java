package gameoflife.controller;

import gameoflife.model.Cell;
import gameoflife.view.DisplayCells;
import gameoflife.view.GameScene;


public class Cycle {

    private Long cycleCount = 0L;
    private Cell[] cells;
    private DisplayCells display;
    private GameScene scene;

    public Cycle(GameScene scene, Cell[] cells, DisplayCells display) {
        this.cells = cells;
        this.display = display;
        this.scene = scene;

    }

    public void run() {
        calculateAllNewStatesForArray();
        setAllNewStatesForCells();
        display.updateView();
        cycleCount = cycleCount + 1;
        scene.cycleUpdate(cycleCount);
    }

    private void calculateAllNewStatesForArray() {
        for (Cell cell : this.cells) {
            cell.calculateNewState(this.cells);
        }
    }

    private void setAllNewStatesForCells() {
        for (Cell cell : this.cells) {
            cell.updateState();
        }
    }
}