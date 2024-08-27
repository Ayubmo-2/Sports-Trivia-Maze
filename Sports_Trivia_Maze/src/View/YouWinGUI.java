package View;

import javax.swing.*;
import java.awt.*;
/*
 * TCSS 360 - Sports Trivia Maze
 */
/**
 * The YouWinGUI class represents the "You Win" screen displayed when the player wins the game.
 * It shows a congratulatory message in a new window with a styled design.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class YouWinGUI {
    /**
     * Constructs a YouWinGUI object and displays the "You Win" window with a congratulatory message.
     */
    public YouWinGUI() {
        JFrame myWinFrame = new JFrame("You Win!");
        myWinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWinFrame.setSize(400, 300);
        myWinFrame.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(Color.BLACK);

        JLabel congratsLabel = new JLabel("Congratulations!", JLabel.CENTER);
        congratsLabel.setFont(new Font("Algerian", Font.BOLD, 32));
        congratsLabel.setForeground(Color.WHITE);
        congratsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel youWinLabel = new JLabel("You Win!", JLabel.CENTER);
        youWinLabel.setFont(new Font("Algerian", Font.BOLD, 32));
        youWinLabel.setForeground(Color.WHITE);
        youWinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(Box.createVerticalGlue());
        labelPanel.add(congratsLabel);
        labelPanel.add(youWinLabel);
        labelPanel.add(Box.createVerticalGlue());

        myWinFrame.add(labelPanel, BorderLayout.CENTER);

        myWinFrame.setLocationRelativeTo(null);
        myWinFrame.setVisible(true);
    }
}
