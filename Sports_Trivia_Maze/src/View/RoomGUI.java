/*
 * TCSS 360 - Sports Trivia Maze
 */
package View;

import Model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;
/**
 * The RoomGUI class represents the graphical user interface for a room in the Sports Trivia Maze.
 * It contains the player pointer, room panel, and methods to update and manage the doors in the room.
 * The class listens for property changes to update the room state accordingly.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class RoomGUI implements PropertyChangeListener {
    /** A panel representing the player pointer. */
    private final JPanel myPlayerPointer;
    /** An array of buttons representing the doors in the room. */
    private JButton[] myDoors;
    /** The panel representing the room. */
    private final JPanel myRoomPanel;
    /** The current rotation of the player pointer. */
    private int myCurrentRotation;
    /** A label to display messages to the player. */
    private final JLabel myMessageLabel;
    /** Saves the open door button */
    private JButton myOpenDoor;
    /** Supports property change listeners for the room. */
    PropertyChangeSupport myPCS;

    /**
     * Constructs a RoomGUI object with the specified door directions.
     * Initializes the room panel, player pointer, and message label.
     *
     * @param theDoors A string representing the initial state of the doors in the room.
     */
    public RoomGUI(String theDoors) {
        myCurrentRotation = 0;
        myPCS = new PropertyChangeSupport(this);
        myPlayerPointer = createPlayerPointer();
        myMessageLabel = new JLabel("Click On a Door to Pick a Room | Selected: ");
        myMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        myRoomPanel = new JPanel();
        myRoomPanel.setLayout(new BoxLayout(myRoomPanel, BoxLayout.Y_AXIS));
        myDoors = new JButton[4];
        updateRoom(theDoors);
        myRoomPanel.setPreferredSize(new Dimension(150, 150));
        myRoomPanel.setMaximumSize(new Dimension(150, 150));
        myRoomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    /**
     * Adds a property change listener to the room.
     *
     * @param theListener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
    /**
     * Updates the room with the new state of the doors based on the specified directions.
     *
     * @param theDoorDirections A string representing the current state of the doors in the room.
     */
    private void updateRoom(String theDoorDirections) {
        myRoomPanel.removeAll();
        JButton[] doorButtons = new JButton[4];
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel eastwestPanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel(new BorderLayout());

        Dimension verticalDimension = new Dimension(75, 15);
        Dimension horizontalDimension = new Dimension(15, 75);

        Font buttonFont = new Font("Monospaced", Font.BOLD, 12);

        Scanner scan = new Scanner(theDoorDirections);
        scan.next();
        scan.next();

        while (scan.hasNext()) {
            String current = scan.next();
            String direction = scan.next();
            Direction dir = null;
            JButton doorButton = null;

            switch (current) {
                case "NORTH" -> {
                    dir = Direction.NORTH;
                    doorButton = new JButton("^");
                    doorButton.setName(String.valueOf(dir.getIndex()));
                    doorButton.setPreferredSize(verticalDimension);
                    doorButton.setMaximumSize(verticalDimension);
                    northPanel.add(doorButton, BorderLayout.NORTH);
                }
                case "SOUTH" -> {
                    dir = Direction.SOUTH;
                    doorButton = new JButton("v");
                    doorButton.setName(String.valueOf(dir.getIndex()));
                    doorButton.setPreferredSize(verticalDimension);
                    doorButton.setMaximumSize(verticalDimension);
                    southPanel.add(doorButton, BorderLayout.SOUTH);
                }
                case "WEST" -> {
                    dir = Direction.WEST;
                    doorButton = new JButton("<");
                    doorButton.setName(String.valueOf(dir.getIndex()));
                    doorButton.setPreferredSize(horizontalDimension);
                    doorButton.setMaximumSize(horizontalDimension);
                    eastwestPanel.add(doorButton, BorderLayout.WEST);
                }
                case "EAST" -> {
                    dir = Direction.EAST;
                    doorButton = new JButton(">");
                    doorButton.setName(String.valueOf(dir.getIndex()));
                    doorButton.setPreferredSize(horizontalDimension);
                    doorButton.setMaximumSize(horizontalDimension);
                    eastwestPanel.add(doorButton, BorderLayout.EAST);
                }
            }

            if (doorButton != null) {
                doorButton.setFont(buttonFont);
                doorButton.setHorizontalAlignment(SwingConstants.CENTER);
                doorButton.setVerticalAlignment(SwingConstants.CENTER);
                doorButton.setMargin(new Insets(0, 0, 0, 0));

                doorButtons[dir.getIndex()] = doorButton;
                if (direction.equals("LOCKED")) {
                    doorButton.setText("X");
                    doorButton.setEnabled(false);
                    doorButton.setBackground(Color.RED);
                    doorButton.setForeground(Color.WHITE);
                } else {
                    doorButton.setEnabled(true);
                    if (direction.equals("CLOSED")) {
                        doorButton.setBackground(Color.WHITE);
                        doorButton.setForeground(Color.BLACK);
                    } else if (direction.equals("OPEN")) {
                        doorButton.setBackground(Color.GREEN);
                        doorButton.setForeground(Color.BLACK);
                    }
                }
                doorButton.addActionListener(theEvent -> {
                    JButton clickedButton = (JButton) theEvent.getSource();
                    String direction1 = clickedButton.getText();
                    myMessageLabel.setText("Click On a Door to Pick a Room | Selected: " + direction1);

                    for (JButton button : myDoors) {
                        if (button != null && button.getBackground().equals(Color.GREEN)) {
                            button.setEnabled(false);
                            myOpenDoor = button;
                        }
                    }
                    myPCS.firePropertyChange(new PropertyChangeEvent(clickedButton.getName(),
                            "door_chosen",
                            "closed",
                            "prompted"));
                });
                doorButton.addMouseListener(new PointerListener());
            }
        }

        eastwestPanel.add(myPlayerPointer, BorderLayout.CENTER);

        myRoomPanel.setLayout(new BorderLayout());
        myRoomPanel.add(northPanel, BorderLayout.NORTH);
        myRoomPanel.add(eastwestPanel, BorderLayout.CENTER);
        myRoomPanel.add(southPanel, BorderLayout.SOUTH);

        myDoors = doorButtons;
        myRoomPanel.revalidate();
        myRoomPanel.repaint();
    }
    /**
     * Enables all door buttons in the room.
     */
    private void enableDoorButtons() {
        for (JButton button : myDoors) {
            if (button != null && !button.getText().equals("X")) {
                button.setEnabled(true);
            }
        }
    }
    /**
     * Gets the room panel.
     *
     * @return The JPanel representing the room.
     */
    public JPanel getRoomPanel() {
        return myRoomPanel;
    }
    /**
     * Gets the message board label used to display messages to the player.
     *
     * @return The JLabel representing the message board.
     */
    public JLabel getMessageBoard() {
        return myMessageLabel;
    }

    /**
     * Creates a JPanel representing the player pointer with a given color.
     *
     * @return The JPanel representing the player pointer.
     */
    private JPanel createPlayerPointer() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics theGraphics) {
                super.paintComponent(theGraphics);
                setBackground(Color.BLACK);
                Graphics2D g2d = (Graphics2D) theGraphics;
                g2d.setColor(Color.GREEN);
                g2d.rotate(Math.toRadians(myCurrentRotation), (double) getWidth() / 2, (double) getHeight() / 2);
                int[] xPoints = {getWidth() / 2, getWidth() / 4, 3 * getWidth() / 4};
                int[] yPoints = {getHeight() / 4, 3 * getHeight() / 4, 3 * getHeight() / 4};
                g2d.fillPolygon(xPoints, yPoints, 3);
            }
        };
        panel.setPreferredSize(new Dimension(75, 75));
        panel.setMaximumSize(new Dimension(75, 75));
        return panel;
    }
    /**
     * Locks the button corresponding to the specified direction, hiding it from the UI.
     *
     * @param direction The direction of the button to lock.
     */
    private void lockRoomButton(Direction direction) {
        JButton buttonToLock = myDoors[direction.getIndex()];
        if (buttonToLock != null) {
            buttonToLock.setText("X");
            buttonToLock.setEnabled(false);
            buttonToLock.setBackground(Color.RED);
            buttonToLock.setForeground(Color.WHITE);

            for (ActionListener al : buttonToLock.getActionListeners()) {
                buttonToLock.removeActionListener(al);
            }
            myRoomPanel.repaint();
        }
    }
    /**
     * Handles property change events related to room updates, game completion, and questions answered.
     *
     * @param theEvent The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if ("room_update".equals(theEvent.getPropertyName())) {
            updateRoom((String) theEvent.getNewValue());
            enableDoorButtons();
        } else if ("game_completed".equals(theEvent.getPropertyName())) {
            SwingUtilities.getWindowAncestor(myRoomPanel).dispose();
            new YouWinGUI();
        } else if ("game_lost".equals(theEvent.getPropertyName())) {
            SwingUtilities.getWindowAncestor(myRoomPanel).dispose();
            new YouLoseGUI();
        } else if ("question_answered".equals(theEvent.getPropertyName())) {
            boolean isCorrect = (boolean) theEvent.getNewValue();
            QuestionGUI questionGUI = (QuestionGUI) theEvent.getSource();
            questionGUI.clearQuestion();
            if (isCorrect) {
                enableDoorButtons();
            }
        } else if ("reenable_open_doors".equals(theEvent.getPropertyName())) {
            for (JButton button : myDoors) {
                if (button != null && button.getBackground().equals(Color.GREEN)) {
                    button.setEnabled(true);
                }
            }
        } else if ("room_locked".equals(theEvent.getPropertyName())) {
            String[] roomCoords = ((String) theEvent.getNewValue()).split(" ");
            Direction lockedDirection = (Direction) theEvent.getOldValue();
            if (lockedDirection != null) {
                lockRoomButton(lockedDirection);
            }
        }
    }
    /**
     * The PointerListener class is a mouse adapter that listens for mouse events on door buttons.
     * When the mouse enters a button, it adjusts the rotation of the player pointer to match the direction
     * indicated by the button and repaints the pointer.
     *
     * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
     * version 16 August 2024
     */
    private class PointerListener extends MouseAdapter {
        /**
         * Invoked when the mouse enters a door button. Adjusts the rotation of the player pointer
         * to match the direction of the button and repaints the pointer.
         *
         * @param theEvent The mouse event triggered when the mouse enters the button.
         */
        @Override
        public void mouseEntered(MouseEvent theEvent) {
            myCurrentRotation = Integer.parseInt(((JButton) theEvent.getSource()).getName()) * 90;
            myPlayerPointer.repaint();
        }
    }
}