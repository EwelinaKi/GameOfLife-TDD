package gameoflife.model;

import gameoflife.controller.Main;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private static final Cell[] CELLS_LIST = Main.createCellsList(3, 3);

    @Test
    public void shouldHaveProperNeighborsAtUpperLeftCorner() {
        // when
        int[] neighbors = CELLS_LIST[0].getNeighbors();

        // then
        int[] expected = new int[]{1, 3, 4};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtUpperEdge() {
        // when
        int[] neighbors = CELLS_LIST[1].getNeighbors();

        // then
        int[] expected = new int[]{0, 2, 3, 4, 5};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtUpperRightCorner() {
        // when
        int[] neighbors = CELLS_LIST[2].getNeighbors();

        //then
        int[] expected = new int[]{1, 4, 5};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtLeftEdge() {
        // when
        int[] neighbors = CELLS_LIST[3].getNeighbors();

        //then
        int[] expected = new int[]{0, 1, 4, 6, 7};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtCenter() {
        // when
        int[] neighbors = CELLS_LIST[4].getNeighbors();

        //then
        int[] expected = new int[]{0, 1, 2, 3, 5, 6, 7, 8};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtRightEdge() {
        // when
        int[] neighbors = CELLS_LIST[5].getNeighbors();

        //then
        int[] expected = new int[]{1, 2, 4, 7, 8};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtBottomLeftCorner() {
        // when
        int[] neighbors = CELLS_LIST[6].getNeighbors();

        //then
        int[] expected = new int[]{3, 4, 7};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtBottomEdge() {
        // when
        int[] neighbors = CELLS_LIST[7].getNeighbors();

        //then
        int[] expected = new int[]{3, 4, 5, 6, 8};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtBottomRightCorner() {
        // when
        int[] neighbors = CELLS_LIST[8].getNeighbors();

        //then
        int[] expected = new int[]{4, 5, 7};
        assertArrayEquals(expected, neighbors);
    }

    @Test
    public void shouldCalculateStateForCellWith1LivingNeighbors() {
        // given
        Cell[] cellsList = Main.createCellsList(3, 3);
        Cell controlCell = cellsList[4];

        // when
        cellsList[0].revive();
        controlCell.revive();
        Main.CELLS = cellsList;
        controlCell.calculateNewState();

        // then underpopulation should kill this cell
        assertFalse(controlCell.isGoingToLive());
    }

    @Test
    public void shouldCalculateStateForCellWith2LivingNeighbors() {
        // given
        Cell[] cellsList = Main.createCellsList(3, 3);
        Cell controlCell = cellsList[4];
        cellsList[0].revive();
        cellsList[1].revive();

        // when cell is living
        controlCell.revive();
        Main.CELLS = cellsList;
//        boolean oldStateForLiving = controlCell.isAlive();
        controlCell.calculateNewState();
//        boolean newStateForLiving = controlCell.isGoingToLive();
        boolean newStateForLiving = controlCell.isAlive();

        //when cell is dead
        controlCell.kill();
        Main.CELLS = cellsList;
//        boolean oldStateForDead = controlCell.isAlive();
        controlCell.calculateNewState();
        boolean newStateForDead = controlCell.isAlive();

        // then should not change its state
        assertEquals(newStateForLiving, true);
        assertEquals(newStateForDead, false);
    }

    @Test
    public void shouldCalculateStateForCellWith3LivingNeighbors() {
        // given
        Cell[] cellsList = Main.createCellsList(3, 3);
        Cell controlCell = cellsList[4];

        // when
        cellsList[0].revive();
        cellsList[1].revive();
        cellsList[2].revive();
        Main.CELLS = cellsList;
        controlCell.calculateNewState();

        // then should revive
        assertTrue(controlCell.isGoingToLive());
    }

    @Test
    public void shouldCalculateStateForCellWith4LivingNeighbors() {
        // given
        Cell[] cellsList = Main.createCellsList(3, 3);
        Cell controlCell = cellsList[4];

        // when
        cellsList[0].revive();
        cellsList[1].revive();
        cellsList[2].revive();
        cellsList[3].revive();
        controlCell.revive();
        Main.CELLS = cellsList;
        controlCell.calculateNewState();
        controlCell.updateState();

        // then should die due to overpopulation
        assertFalse(controlCell.isAlive());
    }
}


