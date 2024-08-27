package Controller;

import View.StartMenuGUI;

/**
 * The sportsTriviaController class serves as the entry point for the Sports Trivia Maze application.
 * It initializes the start menu GUI, allowing the user to choose a trivia category and begin the game.
 */
public class sportsTriviaController {

    /**
     * The main method of the application.
     * It creates and displays the start menu GUI, from which the user can start the game.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {

        new StartMenuGUI();
    }
}