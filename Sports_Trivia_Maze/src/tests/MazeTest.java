package tests;

import Model.Direction;
import Model.DoorState;
import Model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeTest {
    private TestMaze myMaze;

    @BeforeEach
    void setUp() {
        myMaze = new TestMaze(4,4);
        myMaze.initializeMaze("ALL");
    }

    @Test
    void initializeMaze() {
        assertEquals(myMaze.myCurrentRoom, myMaze.myMaze[3][3]);
    }

    @Test
    void getMazeHeight() {
        assertEquals(myMaze.getMazeHeight(), 4);
    }

    @Test
    void getMazeWidth() {
        assertEquals(myMaze.getMazeWidth(), 4);

    }

    @Test
    void isGameLost() {
        myMaze.myMaze[3][3].myDoors.get(3).setState(DoorState.LOCKED);
        myMaze.myMaze[3][3].myDoors.get(0).setState(DoorState.LOCKED);
        assertTrue(myMaze.isGameLost(myMaze.myCurrentRoom));

    }

    @Test
    void isGameLostWhenAllNorthDoorsLock() {
        myMaze.myMaze[2][0].myDoors.getFirst().setState(DoorState.LOCKED);
        myMaze.myMaze[2][1].myDoors.getFirst().setState(DoorState.LOCKED);
        myMaze.myMaze[2][2].myDoors.getFirst().setState(DoorState.LOCKED);
        myMaze.myMaze[2][3].myDoors.getFirst().setState(DoorState.LOCKED);
        assertTrue(myMaze.isGameLost(myMaze.myCurrentRoom));

    }


    @Test
    void isGameLostWhenAllWestDoorsLock() {
        myMaze.myMaze[0][2].myDoors.get(3).setState(DoorState.LOCKED);
        myMaze.myMaze[1][2].myDoors.get(3).setState(DoorState.LOCKED);
        myMaze.myMaze[2][2].myDoors.get(3).setState(DoorState.LOCKED);
        myMaze.myMaze[3][2].myDoors.get(3).setState(DoorState.LOCKED);

        assertTrue(myMaze.isGameLost(myMaze.myCurrentRoom));

    }

    @Test
    void getCurrentRoom() {
        assertEquals(myMaze.getCurrentRoom(), "3 3 NORTH CLOSED WEST CLOSED");

    }

    @Test
    void getCurrentRoomAfterMove() {
        myMaze.movePlayer(Direction.NORTH);
        assertEquals(myMaze.getCurrentRoom(), "3 2 NORTH CLOSED SOUTH CLOSED WEST CLOSED");

    }

    @Test
    void getCurrentRoomAfterMove2() {
        myMaze.movePlayer(Direction.NORTH);
        myMaze.movePlayer(Direction.WEST);
        assertEquals(myMaze.getCurrentRoom(), "2 2 NORTH CLOSED EAST CLOSED SOUTH CLOSED WEST CLOSED ");

    }

    @Test
    void createMazeFirstCorner() {
        assertEquals(myMaze.myMaze[0][0].getDoor(Direction.EAST), myMaze.myMaze[0][1].getDoor(Direction.WEST));
        assertEquals(myMaze.myMaze[0][0].getDoor(Direction.SOUTH), myMaze.myMaze[1][0].getDoor(Direction.NORTH));

    }

    @Test
    void createMazeLastCorner() {
        assertEquals(myMaze.myMaze[3][2].getDoor(Direction.EAST), myMaze.myMaze[3][3].getDoor(Direction.WEST));
        assertEquals(myMaze.myMaze[2][3].getDoor(Direction.SOUTH), myMaze.myMaze[3][3].getDoor(Direction.NORTH));

    }

    @Test
    void getVisitedRooms() {
        assertEquals(myMaze.getVisitedRooms(), "");
    }


}