/*
 * TCSS 360 - Sports Trivia Maze
 */

package View;

import Model.Maze;
import javax.swing.*;
import java.awt.*;

/**
 * The StartMenuGUI class is responsible for displaying the start menu of the Sports Trivia Maze game.
 * It allows the user to select a trivia category, view instructions, or exit the game.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class StartMenuGUI {

    /**
     * The JFrame representing the start menu window.
     */
    private final JFrame myStartPanel;

    /**
     * A Toolkit object to retrieve the screen dimensions.
     */
    private static final Toolkit START_MENU_TOOL_KIT = Toolkit.getDefaultToolkit();

    /**
     * The dimensions of the screen.
     */
    private static final Dimension START_SCREEN_SIZE = START_MENU_TOOL_KIT.getScreenSize();

    /**
     * The width of the start menu window.
     */
    private static final int START_SCREEN_WIDTH = START_SCREEN_SIZE.width / 3;

    /**
     * The height of the start menu window.
     */
    private static final int START_SCREEN_HEIGHT = START_SCREEN_SIZE.height / 3;

    /**
     * Constructor for the StartMenuGUI class.
     * Initializes and displays the start menu.
     */
    public StartMenuGUI() {
        myStartPanel = new JFrame("Sports Trivia Maze - Start Menu");
        myStartPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myStartPanel.setSize(START_SCREEN_WIDTH, START_SCREEN_HEIGHT);
        myStartPanel.setLocationRelativeTo(null);

        createStartMenu();
        myStartPanel.setVisible(true);
    }

    /**
     * Creates and displays the main start menu with options to start the game, view instructions, or exit.
     * This method is synchronized to ensure thread safety.
     */
    private synchronized void createStartMenu() {
        myStartPanel.getContentPane().removeAll();
        myStartPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Sports Trivia Maze", JLabel.CENTER);
        myStartPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(theEvent -> createQuestionTypeSelection());

        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsButton.addActionListener(theEvent -> showInstructions());

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(theEvent -> System.exit(0));

        startButtonPanel.add(startButton);
        startButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        startButtonPanel.add(instructionsButton);
        startButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        startButtonPanel.add(exitButton);

        myStartPanel.add(welcomeLabel, BorderLayout.CENTER);
        myStartPanel.add(startButtonPanel, BorderLayout.SOUTH);
        myStartPanel.revalidate();
        myStartPanel.repaint();
    }

    /**
     * Displays the menu for selecting the trivia category.
     * Users can choose from several categories, each represented by a button.
     */
    private void createQuestionTypeSelection() {
        myStartPanel.getContentPane().removeAll();
        myStartPanel.setLayout(new BorderLayout());

        JLabel questionSelectLabel = new JLabel("Select the trivia category:", JLabel.CENTER);
        myStartPanel.add(questionSelectLabel, BorderLayout.NORTH);

        JPanel categoryButtonPanel = new JPanel();
        categoryButtonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        String[] categories = {"ALL", "BASKETBALL", "FOOTBALL", "BASEBALL", "SOCCER"};
        for (String category : categories) {
            JButton categoryButton = new JButton(category);
            categoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoryButton.addActionListener(theEvent -> startGameWithCategory(category));
            categoryButtonPanel.add(categoryButton, gbc);
            gbc.gridy++;
        }

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(theEvent -> createStartMenu());

        categoryButtonPanel.add(backButton, gbc);

        myStartPanel.add(categoryButtonPanel, BorderLayout.CENTER);
        myStartPanel.revalidate();
        myStartPanel.repaint();
    }

    /**
     * Displays the instructions window, which contains multiple pages of instructions for the game.
     */
    private void showInstructions() {
        JFrame instructionsFrame = new JFrame("Instructions");
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructionsFrame.setSize(400, 300);
        instructionsFrame.setLayout(new BorderLayout());

        // Instruction text explaining the game
        String instructionText =
                """
                        Welcome to the Sports Trivia Maze game!

                        Objective: Navigate through the maze by answering sports-related trivia questions. \
                        Each room in the maze has doors leading in different directions. \
                        To open a door and move to the next room, you must correctly answer a trivia question.

                        Gameplay:
                        - Answer the trivia question presented when you select a door.
                        - If you answer correctly, the door will open, and you can proceed to the next room.
                        - If you answer incorrectly, the door will lock, preventing you from passing through. \
                        You'll need to choose a different door.

                        The game ends when you successfully reach the exit of the maze. \
                        Good luck and enjoy the challenge!""";


        JTextArea instructionTextArea = new JTextArea(instructionText);
        instructionTextArea.setLineWrap(true);
        instructionTextArea.setWrapStyleWord(true);
        instructionTextArea.setEditable(false);
        instructionTextArea.setBackground(instructionsFrame.getBackground());
        instructionTextArea.setMargin(new Insets(10, 10, 10, 10));

        instructionsFrame.add(new JScrollPane(instructionTextArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> instructionsFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        instructionsFrame.add(buttonPanel, BorderLayout.SOUTH);
        instructionsFrame.setLocationRelativeTo(myStartPanel);
        instructionsFrame.setVisible(true);
    }

    /**
     * Starts the game with the selected trivia category.
     * This method initializes the game components and closes the start menu.
     *
     * @param theCategory The selected trivia category.
     */
    private void startGameWithCategory(String theCategory) {
        Maze maze = Maze.getInstance(); // Get or create the Maze instance
        maze.initializeMaze(theCategory);
        myStartPanel.dispose();

        MazeGUI mazegui = new MazeGUI(maze.getMazeHeight(), maze.getMazeWidth());
        QuestionGUI questiongui = new QuestionGUI();
        RoomGUI roomgui = new RoomGUI(maze.getCurrentRoom());

        // Add listeners and start the game
        questiongui.addPropertyChangeListener(maze);
        roomgui.addPropertyChangeListener(maze);
        maze.addPropertyChangeListener(questiongui);
        maze.addPropertyChangeListener(roomgui);
        maze.addPropertyChangeListener(mazegui);

        // Create and display the main GUI
        new MainGUI(questiongui, roomgui, mazegui);
    }
}