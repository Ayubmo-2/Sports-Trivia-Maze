/*
 * TCSS 360 - Sports Trivia Maze
 */

package View;

import java.awt.*;
import javax.swing.*;

/**
 * The MainGUI class sets up the main graphical user interface for the Sports Trivia Maze application.
 * It initializes the main frame, panels, and layout, integrating different components like the maze, rooms, and questions.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class MainGUI {

    /** A Toolkit for the size of the GUI. */

    private static final Toolkit TOOL_KIT = Toolkit.getDefaultToolkit();

    /** The Dimensions of my frame. */

    private static final Dimension SCREEN_SIZE = TOOL_KIT.getScreenSize();

    /** The Dimensions for the width of the GUI. */

    private static final int SCREEN_WIDTH = SCREEN_SIZE.width * 3 / 4;

    /** The Dimensions for the height of the GUI. */

    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height * 3 / 4;

    /**
     * Constructs the MainGUI and sets up the main frame and panels for the application.
     *
     * @param theQuestionGUI The QuestionGUI object representing the question panel.
     * @param theRoomGUI The RoomGUI object representing the room panel and message board.
     * @param theMazeGUI The MazeGUI object representing the maze panel.
     */
    public MainGUI(QuestionGUI theQuestionGUI, RoomGUI theRoomGUI, MazeGUI theMazeGUI) {
        JFrame myMainFrame = new JFrame("Sports Trivia Maze");
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        myMainFrame.setLocationRelativeTo(null);
        JPanel myMainPanel = new JPanel();
        myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.X_AXIS));

        FileMenuBar myMainMenuBar = new FileMenuBar();
        myMainFrame.setJMenuBar(myMainMenuBar);



        myMainFrame.add(myMainPanel);

        Dimension minSize = new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()/8);
        Dimension prefSize = new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()/8);
        Dimension maxSize = new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()/8);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(((myMainFrame.getWidth()/2)), myMainFrame.getHeight()));
        leftPanel.setMaximumSize(new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()));
        leftPanel.add(new Box.Filler(minSize, prefSize, maxSize));
        leftPanel.add(theMazeGUI.getMazePanel(), Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(new Box.Filler(minSize, prefSize, maxSize));
        leftPanel.add(theRoomGUI.getRoomPanel(), Component.CENTER_ALIGNMENT);
        leftPanel.add(theRoomGUI.getMessageBoard(), Component.CENTER_ALIGNMENT);
        leftPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setMinimumSize(new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()));
        rightPanel.setPreferredSize(new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()));
        rightPanel.setMaximumSize(new Dimension((myMainFrame.getWidth()/2), myMainFrame.getHeight()));
        rightPanel.add(new Box.Filler(minSize, prefSize, maxSize));
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(theQuestionGUI.getQuestionPanel(), Component.CENTER_ALIGNMENT);
        rightPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        myMainPanel.add(leftPanel);
        myMainPanel.add(rightPanel);

        myMainFrame.setResizable(false);
        myMainFrame.setVisible(true);
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
