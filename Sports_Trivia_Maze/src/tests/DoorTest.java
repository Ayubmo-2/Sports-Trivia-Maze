package tests;

import Model.Door;
import Model.DoorState;
import Model.Question;
import Model.TrueFalseQuestion;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {
    private TestDoor myDoor;
    private Question myQuestion;
    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        myQuestion = new TrueFalseQuestion("This is a test", "True");
        myDoor = new TestDoor(myQuestion);

    }

    @org.junit.jupiter.api.Test
    void getState() {
        assertEquals(myDoor.getState(), DoorState.CLOSED);
    }

    @org.junit.jupiter.api.Test
    void getQuestion() {
        assertEquals(myDoor.getQuestion(), myQuestion);

    }

    @org.junit.jupiter.api.Test
    void setState() {
        myDoor.setState(DoorState.LOCKED);
        assertEquals(myDoor.getState() , DoorState.LOCKED);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals(myDoor.toString(), "Door# " +myDoor.hashCode());
    }
}