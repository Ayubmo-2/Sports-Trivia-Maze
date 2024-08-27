/*
 * TCSS 360 - Sports Trivia Maze
 */

package View;

import javax.swing.*;

/**
 * The FileMenuBar class extends JMenuBar to create a custom menu bar
 * for the application. It includes a File menu with various game options and
 * additional buttons for navigation and information.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class FileMenuBar extends JMenuBar {

    /**
     * Constructs a FileMenuBar and sets up the menu bar with its components.
     */
    public FileMenuBar() { setUpMenuBar(); }

    /**
     * Sets up the menu bar by adding the "File" menu and additional buttons ("Home" and "About").
     */
    private void setUpMenuBar() {
        JMenu fileMenu = setupFileMenu();
        add(fileMenu);

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(theEvent -> new StartMenuGUI());
        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(null,
                        " Sports Trivia Maze: Navigate through a maze by answering sports-related trivia questions.\n " +
                                "Answer correctly to advance. Beware—incorrect answers will lock the door, blocking your path!"));
        add(homeButton);
        add(aboutButton);
    }

    /**
     * Sets up the "File" menu with options for starting a new game, saving the game, reloading the game,
     * and quitting the game. Each menu item is associated with an action listener to handle user interactions.
     *
     * @return The configured JMenu object representing the File menu.
     */
    private JMenu setupFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem newGame = new JMenuItem("New");
        newGame.addActionListener(theEvent -> {

        });
        final JMenuItem saveGame = new JMenuItem("Save");
        saveGame.addActionListener(theEvent -> {

        });
        final JMenuItem reloadGame = new JMenuItem("Reload");
        reloadGame.addActionListener(theEvent -> {

        });
        final JMenuItem quitGame = new JMenuItem("Quit");
        quitGame.addActionListener(theEvent -> {
            int userResponse = JOptionPane.showConfirmDialog(
                    null, "Are you sure you want to quit the game?",
                    "Confirm your choice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (userResponse == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        fileMenu.add(newGame);
        fileMenu.add(saveGame);
        fileMenu.add(reloadGame);
        fileMenu.add(quitGame);
        return fileMenu;
    }
}