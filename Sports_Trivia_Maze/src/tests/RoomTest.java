package tests;

import Model.Direction;
import Model.Question;
import Model.TrueFalseQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private TestRoom myRoom;
    private TestDoor myTestDoor1;
    private TestDoor myTestDoor2;
    private TestDoor myTestDoor3;
    private TestDoor myTestDoor4;

    @BeforeEach
    void setUp() {
        myRoom = new TestRoom(new Point(0,0));
        Question question = new TrueFalseQuestion("test", "test");
        myTestDoor1 = new TestDoor(question);
        myTestDoor2 = new TestDoor(question);
        myTestDoor3 = new TestDoor(question);
        myTestDoor4 = new TestDoor(question);
        myRoom.setDoor(myTestDoor1, Direction.NORTH);
        myRoom.setDoor(myTestDoor2, Direction.EAST);
        myRoom.setDoor(myTestDoor3, Direction.SOUTH);
        myRoom.setDoor(myTestDoor4, Direction.WEST);


    }

    @Test
    void wasVisited() {
        assertFalse(myRoom.wasVisited());
    }

    @Test
    void setNeighboringRoom() {
        TestRoom room = new TestRoom(new Point(2,2));
        myRoom.setNeighboringRoom(Direction.NORTH, room);
        assertEquals(myRoom.getNeighboringRoom(Direction.NORTH), room);
    }

    @Test
    void getNeighboringRoom() {
        TestRoom room = new TestRoom(new Point(1,2));
        myRoom.setNeighboringRoom(Direction.EAST, room);
        assertEquals(myRoom.getNeighboringRoom(Direction.EAST), room);

    }

    @Test
    void setRoomVisited() {
        myRoom.setRoomVisited(true);
        assertTrue(myRoom.wasVisited());
    }

    @Test
    void getDoor() {
        assertEquals(myRoom.getDoor(Direction.NORTH), myTestDoor1);
        assertEquals(myRoom.getDoor(Direction.EAST), myTestDoor2);
        assertEquals(myRoom.getDoor(Direction.SOUTH), myTestDoor3);
        assertEquals(myRoom.getDoor(Direction.WEST), myTestDoor4);

    }

    @Test
    void setDoor() {
        TestDoor door = new TestDoor(new TrueFalseQuestion("test", "test"));
        myRoom.setDoor(door, Direction.EAST);
        assertEquals(door, myRoom.getDoor(Direction.EAST));
        myRoom.setDoor(door, Direction.NORTH);
        assertEquals(door, myRoom.getDoor(Direction.NORTH));
        myRoom.setDoor(door, Direction.WEST);
        assertEquals(door, myRoom.getDoor(Direction.WEST));
        myRoom.setDoor(door, Direction.SOUTH);
        assertEquals(door, myRoom.getDoor(Direction.SOUTH));

    }

    @Test
    void getDoors() {
        assertEquals(myRoom.getDoors().getFirst(), myRoom.getDoor(Direction.NORTH));
        assertEquals(myRoom.getDoors().get(1), myRoom.getDoor(Direction.EAST));
        assertEquals(myRoom.getDoors().get(2), myRoom.getDoor(Direction.SOUTH));
        assertEquals(myRoom.getDoors().get(3), myRoom.getDoor(Direction.WEST));

    }

    @Test
    void getX() {
        assertEquals(myRoom.getX(), 0);
    }

    @Test
    void getY() {
        assertEquals(myRoom.getY(), 0);

    }

    @Test
    void getCoordinates() {
        assertEquals(myRoom.getCoordinates(), "0 0");
    }

    @Test
    void getRoom() {
        assertEquals(myRoom.getRoom(), "Room: " + myRoom.getX() +", " +myRoom.getY()+ " NORTH: " + myRoom.getDoor(Direction.NORTH) + " EAST: " + myRoom. getDoor(Direction.EAST) + " SOUTH: " + myRoom.getDoor(Direction.SOUTH) + " WEST: " + myRoom.getDoor(Direction.WEST));
    }

    @Test
    void testToString() {
        assertEquals(myRoom.toString(), "Room: 0 0");
    }
}