import main.java.gameOfLife.controller.GameLoop;
import main.java.gameOfLife.controller.Main;
import main.java.gameOfLife.model.Cell;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {

    @Test
    public void checkImmutablePatternBlock() {
        // given
        Cell[] cells = Main.createCellsList(4, 4);
        int[] blockIN = new int[]{5, 6, 9, 10};

        //when
        cells = setInitialPattern(cells, blockIN);
        int[] blockOUT = getPatternAfterGameLoop(cells, 1);

        // then
        assertEquals(blockIN, blockOUT);
    }

    @Test
    public void checkImmutablePatternLoaf() {
        // given
        Cell[] cells = Main.createCellsList(4, 4);
        int[] tubIN = new int[]{1, 2, 4, 7, 9, 11, 14};

        //when
        cells = setInitialPattern(cells, tubIN);
        int[] tubOUT = getPatternAfterGameLoop(cells, 1);

        // then
        assertEquals(tubIN, tubOUT);
    }

    @Test
    public void checkOscilatorPatternBlinker() {
        // given
        Cell[] cells = Main.createCellsList(3, 3);
        int[] blinkerIN = new int[]{1, 4, 7};

        //when
        cells = setInitialPattern(cells, blinkerIN);
        int[] blinkerOUT = getPatternAfterGameLoop(cells, 2);

        // then
        assertEquals(blinkerIN, blinkerOUT);
    }

    @Test
    public void checkOscilatorPatternPulsar() {
        // given
        Cell[] cells = Main.createCellsList(15, 15);
        int[] pulsarIN = new int[]{18, 19, 20, 24, 25, 26, 46, 51, 53, 58, 61, 66, 68, 73, 76, 81, 83,
                88, 93, 94, 95, 99, 100, 101, 123, 124, 125, 129, 130, 131, 136, 141, 143, 148, 151, 156,
                158, 163, 166, 171, 173, 178, 198, 199, 200, 204, 205, 206};

        //when
        cells = setInitialPattern(cells, pulsarIN);
        int[] pulsarOUT = getPatternAfterGameLoop(cells, 3);

        // then
        assertEquals(pulsarIN, pulsarOUT);

    }

    private int[] checkSetPattern(Cell[] cells) {
        int[] pattern = new int[0];
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].isAlive()) {
                ArrayUtils.add(pattern, i);
            }
        }
        return pattern;
    }

    private Cell[] setInitialPattern(Cell[] cells, int[] block) {
        for (int index : block) {
            cells[index].revive();
        }
        return cells;
    }

    private int[] getPatternAfterGameLoop(Cell[] cells, int loops) {
        Main.cells = cells;
        for (int i = 0; i < loops; i++) {
            GameLoop.run();
        }
        return checkSetPattern(Main.cells);
    }


}
