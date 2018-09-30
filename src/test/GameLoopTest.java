import main.java.gameOfLife.controller.GameLoop;
import main.java.gameOfLife.controller.Main;
import main.java.gameOfLife.model.Cell;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class GameLoopTest {

    @Test
    public void checkImmutablePatternBlock() {
        // given
        Main.cells = Main.createCellsList(4, 4);
        int[] blockIN = new int[]{5, 6, 9, 10};

        //when
        setInitialPattern(blockIN);
        int[] blockOUT = getPatternAfterGameLoop(1);

        // then
        assertArrayEquals(blockIN, blockOUT);
    }

    @Test
    public void checkImmutablePatternLoaf() {
        // given
        Main.cells= Main.createCellsList(4, 4);
        int[] tubIN = new int[]{1, 2, 4, 7, 9, 11, 14};

        //when
        setInitialPattern(tubIN);
        int[] tubOUT = getPatternAfterGameLoop(1);

        // then
        assertArrayEquals(tubIN, tubOUT);
    }

    @Test
    public void checkOscillatorPatternBlinker() {
        // given
        Main.cells = Main.createCellsList(3, 3);
        int[] blinkerIN = new int[]{1, 4, 7};

        //when
        setInitialPattern(blinkerIN);
        int[] blinkerOUT = getPatternAfterGameLoop(2);

        // then
        assertArrayEquals(blinkerIN, blinkerOUT);
    }

    @Test
    public void checkOscillatorPatternPulsar() {
        // given
        Main.cells = Main.createCellsList(15, 15);
        int[] pulsarIN = new int[]{18, 19, 20, 24, 25, 26, 46, 51, 53, 58, 61, 66, 68, 73, 76, 81, 83,
                88, 93, 94, 95, 99, 100, 101, 123, 124, 125, 129, 130, 131, 136, 141, 143, 148, 151, 156,
                158, 163, 166, 171, 173, 178, 198, 199, 200, 204, 205, 206};

        //when
        setInitialPattern(pulsarIN);
        int[] pulsarOUT = getPatternAfterGameLoop(3);

        // then
        assertArrayEquals(pulsarIN, pulsarOUT);
    }

    private int[] checkSetPattern(Cell[] cells) {
        List<Integer> pattern = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            if (Main.cells[i].isAlive()) {
                pattern.add(i);
            }
        }
        return pattern.stream().mapToInt(i -> i).toArray();
    }

    private void setInitialPattern(int[] block) {
        for (int index : block) {
            Main.cells[index].revive();
        }
    }

    private int[] getPatternAfterGameLoop(int loops) {
        for (int i = 0; i < loops; i++) {
            GameLoop.run();
        }
        return checkSetPattern(Main.cells);
    }
}
