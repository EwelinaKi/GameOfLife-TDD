import main.java.gameOfLife.controller.Main;
import main.java.gameOfLife.model.Cell;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private static final Cell[] CELLS_LIST = Main.createCellsList(3, 3);
    private static final Cell[] SMALL_CELLS_LIST = Main.createCellsList(1, 2);

    @Test
    public void shouldHaveProperNeighborsAtUpperLeftCorner() {
        // when
        int[] neighbors = CELLS_LIST[0].getNeighbors();

        // then
        int[] expected = new int[]{1, 3, 4};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtUpperEdge() {
        // when
        int[] neighbors = CELLS_LIST[1].getNeighbors();

        // then
        int[] expected = new int[]{0, 2, 3, 4, 5};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtUpperRightCorner() {
        // when
        int[] neighbors = CELLS_LIST[2].getNeighbors();

        //then
        int[] expected = new int[]{1, 4, 5};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtLeftEdge() {
        // when
        int[] neighbors = CELLS_LIST[3].getNeighbors();

        //then
        int[] expected = new int[]{0, 1, 4, 6, 7};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtCenter() {
        // when
        int[] neighbors = CELLS_LIST[4].getNeighbors();

        //then
        int[] expected = new int[]{0, 1, 2, 3, 5, 6, 7, 8};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtRightEdge() {
        // when
        int[] neighbors = CELLS_LIST[5].getNeighbors();

        //then
        int[] expected = new int[]{1, 2, 4, 7, 8};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtBottomLeftCorner() {
        // when
        int[] neighbors = CELLS_LIST[6].getNeighbors();

        //then
        int[] expected = new int[]{3, 4, 7};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtBottomEdge() {
        // when
        int[] neighbors = CELLS_LIST[7].getNeighbors();

        //then
        int[] expected = new int[]{3, 4, 5, 6, 8};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsAtBottomRightCorner() {
        // when
        int[] neighbors = CELLS_LIST[8].getNeighbors();

        //then
        int[] expected = new int[]{4, 5, 7};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsInSmallArrayForLeftCell() {
        // when
        int[] neighbors = SMALL_CELLS_LIST[0].getNeighbors();

        //then
        int[] expected = new int[]{1};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldHaveProperNeighborsInSmallArrayForRightCell() {
        //when
        int[] neighbors = SMALL_CELLS_LIST[1].getNeighbors();

        // then
        int[] expected = new int[]{0};
        assertEquals(expected, neighbors);
    }

    @Test
    public void shouldCalculateStateForCellWith1LivingNeighbors() {
        // given
        Cell[] cellsList = Main.createCellsList(3, 3);
        Cell controlCell = cellsList[4];

        // when
        cellsList[0].revive();
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
        boolean oldStateForLiving = controlCell.isAlive();
        controlCell.calculateNewState();
        boolean newStateForLiving = controlCell.isGoingToLive();

        //when cell is dead
        controlCell.kill();
        boolean oldStateForDead = controlCell.isAlive();
        controlCell.calculateNewState();
        boolean newStateForDead = controlCell.isGoingToLive();

        // then should not change its state
        assertEquals(newStateForLiving, oldStateForLiving);
        assertEquals(newStateForDead, oldStateForDead);
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
        controlCell.calculateNewState();
        controlCell.updateState();

        // then should die due to overpopulation
        assertFalse(controlCell.isAlive());
    }
}


