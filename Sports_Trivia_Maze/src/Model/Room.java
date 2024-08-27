/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Room class represents a room in a building or a game.
 * It contains information about the doors in the room, the number of rooms,
 * whether the room has a player, and whether the room has been visited.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class Room implements Serializable {

    /**
     * Array of Door objects representing the doors in the room.
     */
    private final List<Door> myDoors;

    /**
     * Indicates whether the room has a player.
     */
    private boolean myRoomHasPlayer;

    /**
     * The neighboring doors.
     */
    private final Room[] myNeighboringRooms;
    /**
     * Indicates whether the room has been visited.
     */
    private boolean myRoomVisited;

    /**
     * The coordinates of the room represented as a Point object.
     */
    private final Point myRoomCoordinates;

    /**
     * Constructs a Room with a specified number of doors.
     *
     * @param theCoordinates The number of doors in the room.
     */
    public Room(Point theCoordinates) {
        myDoors = new ArrayList<>(4);
        myDoors.add(null);
        myDoors.add(null);
        myDoors.add(null);
        myDoors.add(null);
        myRoomHasPlayer = false;
        myRoomVisited = false;
        myRoomCoordinates = theCoordinates;
        myNeighboringRooms = new Room[4];


    }

    /**
     * Checks if the room has a player.
     *
     * @return true if the room has a player, false otherwise.
     */
    public boolean hasPlayer() {
        return myRoomHasPlayer;
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
    public void setNeighboringRoom(Direction theDirection, Room theRoom) {
        myNeighboringRooms[theDirection.getIndex()] = theRoom;
    }

    /**
     * returns the room object of the neighboring room relative to the current door.
     * @param theDirection direction of the room to be returned
     * @return the room object
     */
    public Room getNeighboringRoom(Direction theDirection) {
        return myNeighboringRooms[theDirection.getIndex()];
    }

    /**
     * Sets whether the room has a player.
     *
     * @param theHasPlayer true if the room has a player, false otherwise.
     */
    public void setPlayer(boolean theHasPlayer) {
        myRoomHasPlayer = theHasPlayer;
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
    public Door getDoor(Direction theDirection) {
        return myDoors.get(theDirection.getIndex());

    }

    /**
     * Sets the door in the specified direction for the room.
     *
     * @param theDoor The Door object to set in the specified direction.
     * @param theDirection The direction in which to set the door.
     */
    public void setDoor(Door theDoor, Direction theDirection) {
        myDoors.set(theDirection.getIndex(), theDoor);
    }

    /**
     * Returns the array of doors in the room.
     *
     * @return The array of Door objects.
     */
    public List<Door> getDoors() {
        return myDoors;
    }

    /**
     * Returns the X-coordinate of the room.
     *
     * @return The X-coordinate as an int.
     */
    public int getX() {
        return  myRoomCoordinates.x;
    }

    /**
     * Returns the Y-coordinate of the room.
     *
     * @return The Y-coordinate as an int.
     */
    public int getY() {
        return myRoomCoordinates.y;
    }

    /**
     * Returns the coordinates of the room as a string in the format "X Y".
     *
     * @return A String representing the coordinates of the room.
     */
    public String getCoordinates() {
        return myRoomCoordinates.x + " " + myRoomCoordinates.y;
    }

    /**
     * Returns a string representation of the room, including its coordinates, whether it has a player,
     * and the state of its doors in all four directions (NORTH, EAST, SOUTH, WEST).
     *
     * @return A String representing the details of the room.
     */
    public String getRoom(){
        return " Room: " + getX() +", " +getY()+ " " +hasPlayer() + " NORTH: " + getDoor(Direction.NORTH) + " EAST: " + getDoor(Direction.EAST)+ " SOUTH: " +getDoor(Direction.SOUTH) + " WEST: " +getDoor(Direction.WEST);
    }

    /**
     * Returns a string representation of the room, including its coordinates.
     *
     * @return A String representing the room.
     */
    @Override
    public String toString() { return "Room: " + getCoordinates(); }

}