package tests;

import Model.Direction;
import Model.DoorState;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;

/**
 * The Maze class represents a game maze that players navigate by answering questions.
 * It is implemented as a Singleton, ensuring only one instance of the maze is active at any time.
 * The maze consists of rooms, each connected by doors that require answering questions to pass through.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class TestMaze implements PropertyChangeListener {



    /**
     * The height of the maze, representing the number of rows of rooms.
     */
    public final int myMazeHeight;

    /**
     * The width of the maze, representing the number of columns of rooms.
     */
    public final int myMazeWidth;

    /**
     * A 2D array of Room objects representing the layout of the maze.
     */
    public final TestRoom[][] myMaze;

    /**
     * A list of rooms that have been visited by the player.
     */
    public final List<TestRoom> myVisitedRooms;

    /**
     * The current room where the player is located.
     */
    public TestRoom myCurrentRoom;

    /**
     * The door that the player is currently interacting with.
     */
    public TestDoor myCurrentDoor;

    /**
     * The PropertyChangeSupport object used for managing property change listeners.
     */
    public final PropertyChangeSupport myPCS;

    /**
     * Private constructor for the Maze class, initializes the maze with given dimensions.
     * This constructor is private to enforce the Singleton pattern.
     *
     * @param theHeight The height of the maze (number of rows).
     * @param theWidth  The width of the maze (number of columns).
     */
    public TestMaze(int theHeight, int theWidth) {
        myMazeHeight = theHeight;
        myMazeWidth = theWidth;
        myMaze = new TestRoom[myMazeHeight][myMazeWidth];
        myVisitedRooms = new ArrayList<>();
        myPCS = new PropertyChangeSupport(this);
    }

    /**
     * Sets the category of questions for the maze and initializes the maze layout.
     * This should be called after the user selects a category.
     *
     * @param theCategory The category of questions associated with the doors in the maze.
     */
    public void initializeMaze(String theCategory) {
        createMaze(theCategory);
        myCurrentRoom = myMaze[myMazeHeight - 1][myMazeWidth - 1];
    }

    /**
     * Returns the height of the maze.
     *
     * @return The height of the maze.
     */
    public int getMazeHeight() {
        return myMazeHeight;
    }

    /**
     * Returns the width of the maze.
     *
     * @return The width of the maze.
     */
    public int getMazeWidth() {
        return myMazeWidth;
    }

    /**
     * Displays the current state of the maze to the console.
     * Each room in the maze is printed row by row.
     */
    public void displayMaze() {
        for (int rows = 0; rows < myMazeHeight; rows++) {
            for (int cols = 0; cols < myMazeWidth; cols++) {
                System.out.print(myMaze[rows][cols].getRoom());
            }
            System.out.println();
        }
    }

    /**
     * Adds a PropertyChangeListener to the maze.
     *
     * @param theListener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * Moves the player to a new room based on the direction provided.
     *
     * @param theDirection The direction in which the player wants to move.
     */
    public void movePlayer(Direction theDirection) {
        myCurrentRoom = myMaze[myCurrentRoom.getY() + theDirection.move().y][myCurrentRoom.getX() + theDirection.move().x];
        if (isGameCompleted()) {
            myPCS.firePropertyChange("game_completed", false, true);
        }
    }

    /**
     * Checks if the game is completed by verifying if the player has reached the end room.
     *
     * @return true if the game is completed, false otherwise.
     */
    public boolean isGameCompleted() {
        return myCurrentRoom.getCoordinates().equals("0 0"); // Ensure this matches the end coordinates
    }
    /**
     * Determines if the game is lost by checking if there is no path from the current room
     * to the end room (coordinates "0 0").
     *
     * @param theRoom The current room from which to check the path.
     * @return true if the game is lost (i.e., no path to the end room), false otherwise.
     */
    public boolean isGameLost(TestRoom theRoom) {
        List<TestDoor> traversedDoors = new ArrayList<>();
        List<String> path = new ArrayList<>();
        getPath(theRoom, traversedDoors, path);
        return !path.contains("0 0");

    }
    /**
     * Recursively finds the path from the given room to the end room (coordinates "0 0").
     * The method updates the provided path list and door list as it traverses the maze.
     *
     * @param theRoom     The current room to find the path from.
     * @param theList     The list of doors that have been traversed.
     * @param thePathList The list of coordinates representing the path taken.
     */
    public void getPath(TestRoom theRoom, List<TestDoor> theList, List<String> thePathList) {
        if(theRoom.getCoordinates().equals("0 0")) {
            thePathList.add(theRoom.getCoordinates());
            return;
        }
        for (int i = 0; i < theRoom.getDoors().size(); i++) {
            if (theRoom.getDoor(Direction.getDirectionFromIndex(i)) != null) {
                TestDoor currentDoor = theRoom.getDoor(Direction.getDirectionFromIndex(i));
                if (currentDoor.getState() != DoorState.LOCKED && !theList.contains(currentDoor)) {
                    theList.add(currentDoor);
                    thePathList.add(theRoom.getCoordinates());
                    getPath(theRoom.getNeighboringRoom(Direction.getDirectionFromIndex(i)), theList, thePathList);
                    thePathList.remove(theRoom.getCoordinates());

                }
            }
        }
    }

    /**
     * Retrieves the current room information as a string.
     *
     * @return A string representing the coordinates and door directions of the current room.
     */
    public String getCurrentRoom() {
        StringBuilder str = new StringBuilder(myCurrentRoom.getCoordinates() + " ");
        for (TestDoor door : myCurrentRoom.getDoors()) {
            if (door != null) {
                str.append(Direction.getDirectionFromIndex(myCurrentRoom.getDoors().indexOf(door)).toString()).append(" ");
                str.append(door.getState()).append(" ");
            }
        }
        return str.toString();
    }

    /**
     * Retrieves the coordinates of the visited rooms as a string.
     *
     * @return A string of space-separated coordinates of visited rooms.
     */
    public String getVisitedRooms() {
        StringBuilder roomCoordinates = new StringBuilder();
        for (TestRoom visitedRoom : myVisitedRooms) {
            roomCoordinates.append(visitedRoom.getCoordinates()).append(" ");
        }
        return roomCoordinates.toString();
    }

    /**
     * Handles property change events, such as when a door is chosen or a question is answered.
     *
     * @param theEvent The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if ("door_chosen".equals(theEvent.getPropertyName())) {
            int index = Integer.parseInt((String) theEvent.getSource());
            myCurrentDoor = myCurrentRoom.getDoor(Direction.getDirectionFromIndex(index));
            if(myCurrentDoor.getState().equals(DoorState.OPEN)) {
                if (!myCurrentRoom.wasVisited()) {
                    myCurrentRoom.setRoomVisited(true);
                    myVisitedRooms.add(myCurrentRoom);
                }
                movePlayer(Direction.getDirectionFromIndex(myCurrentRoom.getDoors().indexOf(myCurrentDoor)));
                myPCS.firePropertyChange(new PropertyChangeEvent(this, "room_update", "old", getCurrentRoom()));
                myPCS.firePropertyChange(new PropertyChangeEvent(this, "maze_update", getCurrentRoom(), getVisitedRooms()));
            } else if(myCurrentDoor.getState().equals(DoorState.LOCKED)) {
            }
            else {
                myPCS.firePropertyChange(new PropertyChangeEvent(this, "question_sent", null, myCurrentDoor.getQuestion()));
                System.out.println(myCurrentDoor);
            }
        } else if ("question_answered".equals(theEvent.getPropertyName())) {
            boolean isCorrect = (Boolean) theEvent.getNewValue();
            if (isCorrect) {
                myCurrentDoor.setState(DoorState.OPEN);
                if (!myCurrentRoom.wasVisited()) {
                    myCurrentRoom.setRoomVisited(true);
                    myVisitedRooms.add(myCurrentRoom);
                }
                movePlayer(Direction.getDirectionFromIndex(myCurrentRoom.getDoors().indexOf(myCurrentDoor)));
                myPCS.firePropertyChange(new PropertyChangeEvent(this, "room_update", "old", getCurrentRoom()));
                myPCS.firePropertyChange(new PropertyChangeEvent(this, "maze_update", getCurrentRoom(), getVisitedRooms()));
            } else {
                myCurrentDoor.setState(DoorState.LOCKED);
                Direction lockedDirection = Direction.getDirectionFromIndex(myCurrentRoom.getDoors().indexOf(myCurrentDoor));
                int lockedRoomX = myCurrentRoom.getX() + lockedDirection.move().x;
                int lockedRoomY = myCurrentRoom.getY() + lockedDirection.move().y;

                myPCS.firePropertyChange(new PropertyChangeEvent(this, "room_locked", lockedDirection, lockedRoomX + " " + lockedRoomY));
            }
        }
    }

    /**
     * Creates the maze by initializing rooms and connecting them with doors.
     * The doors are initialized with questions from the selected category.
     */
    public void createMaze(String theCategory) {
        for (int rows = 0; rows < myMazeHeight; rows++) {
            for (int cols = 0; cols < myMazeWidth; cols++) {
                myMaze[rows][cols] = new TestRoom(new Point(cols, rows));
                if (cols != myMazeWidth - 1) {
                    myMaze[rows][cols].setDoor(new TestDoor(DoorState.CLOSED, theCategory), Direction.EAST);
                }
                if (rows != myMazeHeight - 1) {
                    myMaze[rows][cols].setDoor(new TestDoor(DoorState.CLOSED, theCategory), Direction.SOUTH);
                }
                if (cols != 0) {
                    TestDoor west = myMaze[rows][cols - 1].getDoor(Direction.EAST);
                    myMaze[rows][cols].setDoor(west, Direction.WEST);
                    myMaze[rows][cols].setDoor(west, Direction.WEST);
                    myMaze[rows][cols].setNeighboringRoom(Direction.WEST,myMaze[rows][cols-1]);
                    myMaze[rows][cols-1].setNeighboringRoom(Direction.EAST, myMaze[rows][cols]);

                }
                if (rows != 0) {
                    TestDoor north = myMaze[rows - 1][cols].getDoor(Direction.SOUTH);
                    myMaze[rows][cols].setDoor(north, Direction.NORTH);
                    myMaze[rows-1][cols].setNeighboringRoom(Direction.SOUTH, myMaze[rows][cols]);
                    myMaze[rows][cols].setNeighboringRoom(Direction.NORTH, myMaze[rows-1][cols]);
                }
            }
        }
    }
}