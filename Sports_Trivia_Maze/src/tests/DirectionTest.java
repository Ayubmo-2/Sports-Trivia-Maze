package tests;

import Model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getIndex() {
        assertEquals(Direction.NORTH.getIndex(), 0);
        assertEquals(Direction.SOUTH.getIndex(), 2);
        assertEquals(Direction.EAST.getIndex(), 1);
        assertEquals(Direction.WEST.getIndex(), 3);


    }

    @Test
    void getDirectionFromIndex() {
        assertEquals(Direction.getDirectionFromIndex(0), Direction.NORTH);
        assertEquals(Direction.getDirectionFromIndex(2), Direction.SOUTH);
        assertEquals(Direction.getDirectionFromIndex(1), Direction.EAST);
        assertEquals(Direction.getDirectionFromIndex(3), Direction.WEST);


    }


}