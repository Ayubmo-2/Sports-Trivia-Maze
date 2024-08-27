/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

/**
 * The Door class represents a door in the maze, which can be either open, closed, or locked.
 * Each door is associated with a question that must be answered to unlock the door.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class Door {

    /**
     * The state of the door.
     */
    private DoorState myDoorState;

    /**
     * The question associated with the door.
     * The player must answer this question correctly to unlock the door.
     */
    private final Question myQuestion;

    /**
     * Constructs a Door with a specified state and initializes the associated question based on the given category.
     *
     * @param theState    The initial state of the door (OPEN, CLOSED, or LOCKED).
     * @param theCategory The category of the question to be associated with the door.
     */
    public Door(final DoorState theState, String theCategory) {
        myDoorState = theState;
        myQuestion = QuestionFactory.getQuestion(theCategory);
    }

    /**
     * Returns the question associated with the door.
     *
     * @return The Question object associated with the door.
     */
    public Question getQuestion() {
        return myQuestion;
    }

    /**
     * Checks if the door is locked.
     *
     * @return The Door State.
     */
    public DoorState getState() {
        return myDoorState;
    }


    /**
     * Sets the state of the door to the specified state, unless the new state is LOCKED.
     * A door can only transition between OPEN and CLOSED states using this method.
     *
     * @param theDoorState The new state of the door.
     */
    public void setState(DoorState theDoorState) {
        myDoorState = theDoorState;
    }


    /**
     * Returns a string representation of the door.
     * The representation includes the door's unique hash code.
     *
     * @return A string representing the door.
     */
    @Override
    public String toString() {
        return "Door# " + hashCode();
    }
}
