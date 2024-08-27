package tests;

import Model.DoorState;
import Model.Question;
import Model.QuestionFactory;
import Model.TrueFalseQuestion;

/**
 * The Door class represents a door in a building or a game.
 * It contains information about whether the door is locked, the direction of the door,
 * and an associated question.
 */
public class TestDoor {
    /**
     * Indicates whether the door is locked.
     */
    public DoorState myDoorState;

    /**
     * The question associated with the door.
     */
    public final Question myQuestion;

    /**
     * Constructs a Door with specified locked status, direction, and question.
     *
     * @param theState The status of the door.
     */
    public TestDoor(final DoorState theState, String theCategory) {
        myDoorState = theState;
        myQuestion = QuestionFactory.getQuestion(theCategory);
                new TrueFalseQuestion("Are the Seahawks the NFL team of Seattle?", "True");
    }

    /**
     * Constructor allows for custom question creation for testing.
     * @param theQuestion instance of a question
     */
    public TestDoor(Question theQuestion) {
        myDoorState = DoorState.CLOSED;
        myQuestion = theQuestion;
    }

    /**
     * Returns the state of the door.
     *
     * @return t if the door is locked, false otherwise.
     */
    public DoorState getState() {
        return myDoorState;
    }

    /**
     * Returns the question associated with the door.
     *
     * @return The Question object associated with the door.
     */
    public Question getQuestion() {
        return myQuestion;
    }

    public void setState(DoorState theDoorState) {

            myDoorState = theDoorState;
    }


    public String toString() {
        return "Door# " +hashCode();
    }
}