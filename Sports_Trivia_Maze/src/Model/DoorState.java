/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

/**
 * The DoorState enum represents the possible states of a door in the maze.
 * A door can be in one of the following states: OPEN, CLOSED, or LOCKED.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public enum DoorState {

    /**
     * The door is open, allowing the player to pass through.
     */
    OPEN,

    /**
     * The door is closed, requiring the player to answer a question or perform an action to open it.
     */
    CLOSED,

    /**
     * The door is locked, preventing the player from passing through until it is unlocked.
     */
    LOCKED

}