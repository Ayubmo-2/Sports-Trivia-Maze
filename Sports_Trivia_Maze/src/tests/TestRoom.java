package tests;

import Model.Direction;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Room class represents a room in a building or a game.
 * It contains information about the doors in the room, the number of rooms,
 * whether the room has a player, and whether the room has been visited.
 */
public class TestRoom implements Serializable {
    /**
     * Array of Door objects representing the doors in the room.
     */
    public final List<TestDoor> myDoors;

    /**
     * neighboring doors.
     */
    public final TestRoom[] myNeighboringRooms;
    /**
     * Indicates whether the room has been visited.
     */
    public boolean myRoomVisited;

    public final Point myRoomCoordinates;
    /**
     * Constructs a Room with a specified number of doors.
     *
     * @param theCoordinates The number of doors in the room.
     */
    public TestRoom(Point theCoordinates) {
        myDoors = new ArrayList<>(4);
        myDoors.add(null);
        myDoors.add(null);
        myDoors.add(null);
        myDoors.add(null);
        myRoomVisited = false;
        myRoomCoordinates = theCoordinates;
        myNeighboringRooms = new TestRoom[4];


    }

    /**
     * Checks if the room was visited.
     *
     * @return true if the room was visited, false otherwise.
     */
    public boolean wasVisited() {
        return myRoomVisited;
    }

    /**
     * Sets the neighboring door and its direction.
     * @param theDirection direction of the door being added relative to the current room
     * @param theRoom door object to be added to the neighboring doors array
     */
    public void setNeighboringRoom(Direction theDirection, TestRoom theRoom) {
        myNeighboringRooms[theDirection.getIndex()] = theRoom;
    }

    /**
     * returns the room object of the neighboring room relative to the current door.
     * @param theDirection direction of the room to be returned
     * @return the room object
     */
    public TestRoom getNeighboringRoom(Direction theDirection) {
        return myNeighboringRooms[theDirection.getIndex()];
    }

    /**
     * Sets whether the room was visited.
     *
     * @param theVisited true if the room was visited, false otherwise.
     */
    public void setRoomVisited(boolean theVisited) {
        myRoomVisited = theVisited;
    }

    /**
     * Retrieves the door at the specified index in the room.
     *
     * @param theDirection The index of the door to retrieve.
     * @return The Door object at the specified index, or null if the index is out of bounds.
     */
    public TestDoor getDoor(Direction theDirection) {
        return myDoors.get(theDirection.getIndex());

    }

    public void setDoor(TestDoor theDoor, Direction theDirection) {
        myDoors.set(theDirection.getIndex(), theDoor);
    }

    /**
     * Returns the array of doors in the room.
     *
     * @return The array of Door objects.
     */
    public List<TestDoor> getDoors() {
        return myDoors;
    }

    public int getX() {
        return  myRoomCoordinates.x;
    }

    public int getY() {
        return myRoomCoordinates.y;
    }
    public String getCoordinates() {
        return myRoomCoordinates.x + " " + myRoomCoordinates.y;
    }

    public String getRoom(){
        return "Room: " + getX() +", " +getY()+ " NORTH: " + getDoor(Direction.NORTH) + " EAST: " + getDoor(Direction.EAST)+ " SOUTH: " +getDoor(Direction.SOUTH) + " WEST: " +getDoor(Direction.WEST);
    }

    public String toString() {
        return "Room: " + getCoordinates();
    }



}


