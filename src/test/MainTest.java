import main.java.gameOfLife.controller.Main;
import main.java.gameOfLife.model.Cell;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void hasProperArrayOfCellsObjects() {
        // given
        int rows = 100;
        int columns = 100;
        int expected = rows * columns;

        // when
        Cell[] cellsList = Main.createCellsList(rows, columns);

        // then
        long count = Arrays.stream(cellsList).filter(Objects::nonNull).count();
        assertEquals(expected, count);
    }
}


