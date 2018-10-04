package gameoflife.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CycleTest {

    @Test
    public void checkImmutablePatternBlock() {
        // given
        Main.CELLS = Main.createCellsList(4, 4);
        int[] blockIn = new int[]{5, 6, 9, 10};
        setInitialPattern(blockIn);

        //when
        int[] blockOut = getPatternAfterGameLoop(1);

        // then
        assertArrayEquals(blockIn, blockOut);
    }

    @Test
    public void checkImmutablePatternLoaf() {
        // given
        Main.CELLS = Main.createCellsList(4, 4);
        int[] tubIn = new int[]{1, 2, 4, 7, 9, 11, 14};
        setInitialPattern(tubIn);

        //when
        int[] tubOut = getPatternAfterGameLoop(1);

        // then
        assertArrayEquals(tubIn, tubOut);
    }

    @Test
    public void checkOscillatorPatternBlinker() {
        // given
        Main.CELLS = Main.createCellsList(3, 3);
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
        Main.CELLS = Main.createCellsList(15, 15);
        int[] pulsarIN = new int[]{18, 19, 20, 24, 25, 26, 46, 51, 53, 58, 61, 66, 68, 73, 76, 81, 83,
                88, 93, 94, 95, 99, 100, 101, 123, 124, 125, 129, 130, 131, 136, 141, 143, 148, 151, 156,
                158, 163, 166, 171, 173, 178, 198, 199, 200, 204, 205, 206};

        //when
        setInitialPattern(pulsarIN);
        int[] pulsarOUT = getPatternAfterGameLoop(3);

        // then
        assertArrayEquals(pulsarIN, pulsarOUT);
    }

    private int[] checkSetPattern() {
        List<Integer> pattern = new ArrayList<>();

        for (int i = 0; i < Main.CELLS.length; i++) {
            if (Main.CELLS[i].isAlive()) {
                pattern.add(i);
            }
        }
        return pattern.stream().mapToInt(i -> i).toArray();
    }

    private void setInitialPattern(int[] block) {
        for (int index : block) {
            Main.CELLS[index].revive();
        }
    }

    private int[] getPatternAfterGameLoop(int loops) {
        for (int i = 0; i < loops; i++) {
            Cycle.run();
        }
        return checkSetPattern();
    }
}
