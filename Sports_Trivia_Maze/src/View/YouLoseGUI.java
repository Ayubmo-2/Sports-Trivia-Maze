/*
 * TCSS 360 - Sports Trivia Maze
 */
package View;

import javax.swing.*;
import java.awt.*;
/**
 * The YouLoseGUI class represents the "You Lose" screen displayed when the player wins the game.
 * It shows a congratulatory message in a new window with a styled design.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class YouLoseGUI {
    /**
     * Constructs a YouLoseGUI object and displays the "You Lose" window with a congratulatory message.
     */
    public YouLoseGUI() {
        JFrame myLoseFrame = new JFrame("You Lose!");
        myLoseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myLoseFrame.setSize(400, 300);
        myLoseFrame.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(Color.BLACK);

        JLabel sorryLabel = new JLabel("Sorry!", JLabel.CENTER);
        sorryLabel.setFont(new Font("Algerian", Font.BOLD, 32));
        sorryLabel.setForeground(Color.WHITE);
        sorryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel youLoseLabel = new JLabel("You Lose!", JLabel.CENTER);
        youLoseLabel.setFont(new Font("Algerian", Font.BOLD, 32));
        youLoseLabel.setForeground(Color.WHITE);
        youLoseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(Box.createVerticalGlue());
        labelPanel.add(sorryLabel);
        labelPanel.add(youLoseLabel);
        labelPanel.add(Box.createVerticalGlue());

        myLoseFrame.add(labelPanel, BorderLayout.CENTER);

        myLoseFrame.setLocationRelativeTo(null);
        myLoseFrame.setVisible(true);
    }
}
