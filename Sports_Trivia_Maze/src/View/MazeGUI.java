/*
 * TCSS 360 - Sports Trivia Maze
 */
package View;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

/**
 * The MazeGUI class manages the graphical representation of the maze in the Sports Trivia Maze application.
 * It handles the drawing of the maze, updating player position, and reflecting changes based on game events.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class  MazeGUI implements PropertyChangeListener {
    /**
     * JPanel for displaying all gui components.
     */
    private final JPanel myMazePanel;

    /**
     * Height of the maze to draw.
     */
    private int myMazeHeight;

    /**
     * Width of the maze to draw.
     */
    private int myMazeWidth;

    /**
     * 2d array for storing maze labels;
     */
    private final JPanel[][] myMazeArray;
    /**
     * Allows the height and width of the maze to be set.
     *
     * @param theHeight how tall the maze is
     * @param theWidth  how wide the maze is
     */
    public MazeGUI(final int theHeight, final int theWidth) {
        myMazeHeight = 5;
        myMazeWidth = 6;
        if (theHeight > 0 && theHeight < 35) {
            myMazeHeight = theHeight;
        }
        if (theWidth > 0 && theWidth < 35) {
            myMazeWidth = theWidth;
        }
        myMazePanel = new JPanel(new GridLayout(myMazeHeight, myMazeWidth));
        myMazeArray = new JPanel[myMazeHeight][myMazeWidth];
        myMazePanel.setMinimumSize(new Dimension(180, 150));
        myMazePanel.setPreferredSize(new Dimension(300, 250));
        myMazePanel.setMaximumSize(new Dimension(300, 250));
        createMaze();
        drawPlayer(myMazeWidth - 1, myMazeHeight - 1);
        addStartLabel();
        addEndLabel();
        myMazeArray[myMazeHeight - 1][myMazeWidth - 1].setBackground(Color.GREEN);
    }
    /**
     * Returns the panel that contains the maze components.
     *
     * @return The JPanel representing the maze.
     */
    public JPanel getMazePanel() {
        return myMazePanel;
    }
    /**
     * Draws the player's position on the maze at the specified coordinates.
     *
     * @param theX The X-coordinate of the player's position.
     * @param theY The Y-coordinate of the player's position.
     */
    public void drawPlayer(int theX, int theY) {
        if (theX != myMazeWidth - 1 || theY != myMazeHeight - 1) {
            myMazeArray[myMazeHeight - 1][myMazeWidth - 1].removeAll();
            addStartLabel();
        }
        myMazeArray[theY][theX].setBackground(Color.BLACK);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(myMazeArray[0][0].getWidth() / 3, myMazeArray[0][0].getHeight() / 3));
        panel.setBackground(Color.GREEN);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.red),
                panel.getBorder()));
        myMazeArray[theY][theX].setLayout(new GridBagLayout());
        myMazeArray[theY][theX].add(panel);
        myMazeArray[theY][theX].revalidate();
        myMazeArray[theY][theX].repaint();
    }
    /**
     * Updates the maze based on the current room and visited rooms.
     *
     * @param theCurrentRoom  A string representing the coordinates of the current room.
     * @param theVisitedRooms A string representing the coordinates of visited rooms.
     */
    private void updateMaze(String theCurrentRoom, String theVisitedRooms) {
        Scanner visitedScan = new Scanner(theVisitedRooms);
        while (visitedScan.hasNext()) {
            int x = Integer.parseInt(visitedScan.next());
            int y = Integer.parseInt(visitedScan.next());
            myMazeArray[y][x].removeAll();
            if (x != myMazeWidth - 1 || y != myMazeHeight - 1) {
                myMazeArray[myMazeHeight - 1][myMazeWidth - 1].setBackground(Color.BLACK);
            }
            myMazeArray[y][x].setBackground(Color.BLACK);
        }
        Scanner currentScan = new Scanner(theCurrentRoom);
        int x = currentScan.nextInt();
        int y = currentScan.nextInt();
        drawPlayer(x, y);
        myMazePanel.repaint();
    }
    /**
     * Adds a "Start" label to the starting position of the maze.
     */
    private void addStartLabel() {
        JLabel myStartLabel = new JLabel("Start", SwingConstants.CENTER);
        myMazeArray[myMazeHeight - 1][myMazeWidth - 1].setBackground(Color.RED);
        myMazeArray[myMazeHeight - 1][myMazeWidth - 1].add(myStartLabel);
        myMazeArray[myMazeHeight - 1][myMazeWidth - 1].revalidate();
        myMazeArray[myMazeHeight - 1][myMazeWidth - 1].repaint();
    }
    /**
     * Adds an "End" label to the ending position of the maze.
     */
    private void addEndLabel() {
        JLabel myEndLabel = new JLabel("End", SwingConstants.CENTER);
        myMazeArray[0][0].setBackground(Color.GREEN);
        myMazeArray[0][0].add(myEndLabel);
        myMazeArray[0][0].revalidate();
        myMazeArray[0][0].repaint();
    }
    /**
     * Creates the maze by initializing the grid of panels and setting up their borders.
     */
    private void createMaze() {
        for (int rows = 0; rows < myMazeHeight; rows++) {
            for (int cols = 0; cols < myMazeWidth; cols++) {
                myMazeArray[rows][cols] = new JPanel();
                myMazeArray[rows][cols].setBorder(BorderFactory.createLineBorder(Color.black));
                myMazePanel.add(myMazeArray[rows][cols]);
            }
        }
    }
    /**
     * Locks a specific room in the maze by changing its color to yellow.
     *
     * @param x The X-coordinate of the room to lock.
     * @param y The Y-coordinate of the room to lock.
     */
    private void lockRoom(int x, int y) {
        myMazeArray[y][x].setBackground(Color.YELLOW);
        myMazeArray[y][x].revalidate();
        myMazeArray[y][x].repaint();
    }
    /**
     * Handles property change events related to maze updates and room locking.
     *
     * @param theEvent The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("maze_update")) {
            updateMaze((String) theEvent.getOldValue(), (String) theEvent.getNewValue());
        } else if (theEvent.getPropertyName().equals("room_locked")) {
            String[] coords = ((String) theEvent.getNewValue()).split(" ");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            lockRoom(x, y);
        }
    }
}