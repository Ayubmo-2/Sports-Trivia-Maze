/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

import java.awt.*;

/**
 * The Direction enum represents the four cardinal directions: NORTH, EAST, SOUTH, and WEST.
 * Each direction has an associated name, position (represented by a Point), and index.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public enum Direction {

    /**
     * The direction pointing to the south.
     */
    SOUTH("SOUTH", new Point(0,1), 2),

    /**
     * The direction pointing to the north.
     */
    NORTH("NORTH", new Point(0,-1), 0),

    /**
     * The direction pointing to the east.
     */
    EAST("EAST", new Point(1,0), 1),

    /**
     * The direction pointing to the west.
     */
    WEST("WEST", new Point(-1,0), 3);

    /**
     * The position change associated with the direction.
     */
    private final Point myPosition;

    /**
     * The name of the direction.
     */
    private final String myName;

    /**
     * The index of the direction.
     */
    private final int myIndex;

    /**
     * Constructs a Direction enum constant with the specified name, position, and index.
     *
     * @param theName The name of the direction.
     * @param thePlace The position change associated with the direction.
     * @param theIndex The index of the direction.
     */
    Direction(final String theName, final Point thePlace, final int theIndex) {
        myIndex = theIndex;
        myName = theName;
        myPosition = thePlace;
    }

    /**
     * Returns the name of the direction.
     *
     * @return The name of the direction as a String.
     */
    @Override
    public String toString() {
        return myName;
    }

    /**
     * Returns the index of the direction.
     *
     * @return The index of the direction as an int.
     */
    public int getIndex() {
        return myIndex;
    }

    /**
     * Returns a new Point representing the position change associated with this direction.
     *
     * @return A Point object representing the position change.
     */
    public Point move() {
        return (Point) myPosition.clone();
    }

    /**
     * Returns the Direction corresponding to the specified index.
     *
     * @param theIndex The index of the direction.
     * @return The Direction associated with the given index.
     */
    public static Direction getDirectionFromIndex(int theIndex) {
        Direction dir = WEST;
        if(theIndex == 0) {
            dir = NORTH;
        } else if(theIndex == 1) {
            dir = EAST;
        } else if(theIndex == 2) {
            dir = SOUTH;
        }
        return dir;
    }
}