/*
 * TCSS 360 - Sports Trivia Maze
 */
package View;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import Model.MultichoiceQuestion;
import Model.Question;
/**
 * The QuestionGUI class is responsible for displaying questions and receiving user answers in a GUI panel.
 * It supports multiple-choice, true/false, and short answer question types, and interacts with the game logic
 * through property change events.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class QuestionGUI implements PropertyChangeListener {
    /**
     * The main JPanel that contains the question and answer components.
     */
    private final JPanel myQuestionPanel;
    /**
     * JTextArea for displaying the question text.
     */
    private final JTextArea myTextArea;
    /**
     * The user's selected answer.
     */
    private String myAnswer;
    /**
     * PropertyChangeSupport for firing property change events when an answer is selected.
     */
    private final PropertyChangeSupport myPCS;
    /**
     * The current question being displayed.
     */
    private Question myCurrentQuestion;

    /**
     * A Toolkit for the size of the GUI.
     */
    private static final Toolkit TOOL_KIT = Toolkit.getDefaultToolkit();

    /**
     * The Dimensions of my frame.
     */
    private static final Dimension SCREEN_SIZE = TOOL_KIT.getScreenSize();
    /**
     * Constructs a new QuestionGUI object and initializes the question panel.
     */
    public QuestionGUI() {
        myPCS = new PropertyChangeSupport(this);
        myQuestionPanel = new JPanel();
        myQuestionPanel.setLayout(new BoxLayout(myQuestionPanel, BoxLayout.Y_AXIS));

        myTextArea = new JTextArea();
        myTextArea.setEditable(false);
        myTextArea.setLineWrap(true);
        myTextArea.setWrapStyleWord(true);
        myTextArea.setBackground(myQuestionPanel.getBackground());
        myTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        myTextArea.setMargin(new Insets(5, 5, 5, 5));

        myQuestionPanel.add(myTextArea);
        myAnswer = "";

        myQuestionPanel.setPreferredSize(new Dimension(SCREEN_SIZE.width / 4, SCREEN_SIZE.height / 4));
        myQuestionPanel.setMaximumSize(new Dimension(SCREEN_SIZE.width / 4, SCREEN_SIZE.height / 4));
    }
    /**
     * Returns the JPanel containing the question and answer components.
     *
     * @return The question panel.
     */
    public JPanel getQuestionPanel() {
        return myQuestionPanel;
    }
    /**
     * Displays a multiple-choice question with the provided answer options.
     *
     * @param theAnswers An array of possible answers.
     */
    public void multiQuestionDisplay(String[] theAnswers) {
        JPanel multiChoice = new JPanel();
        multiChoice.setLayout(new BoxLayout(multiChoice, BoxLayout.Y_AXIS));
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JButton[] buttons = {new JButton(theAnswers[0]), new JButton(theAnswers[1]), new JButton(theAnswers[2]), new JButton(theAnswers[3])};
        for (JButton button : buttons) {
            button.addActionListener(theEvent -> {
                JButton source = (JButton) theEvent.getSource();
                myAnswer = source.getText();
                processAnswer();
            });
        }
        top.add(buttons[0]);
        top.add(buttons[1]);
        bottom.add(buttons[2]);
        bottom.add(buttons[3]);
        multiChoice.add(top);
        multiChoice.add(bottom);
        myQuestionPanel.add(multiChoice);
    }
    /**
     * Displays a true/false question.
     */
    private void trueFalseQuestionDisplay() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton trueButton = new JButton("True");
        JButton falseButton = new JButton("False");

        trueButton.addActionListener(e -> {
            myAnswer = "True";
            processAnswer();
        });

        falseButton.addActionListener(e -> {
            myAnswer = "False";
            processAnswer();
        });

        panel.add(trueButton);
        panel.add(falseButton);

        myQuestionPanel.add(panel);
        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();
    }
    /**
     * Displays a short answer question with a text field for the user to input their answer.
     */
    private void shortAnswerQuestionDisplay() {
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(200, 30)); // Adjusted for better usability
        text.addActionListener(theEvent -> {
            myAnswer = text.getText();
            processAnswer();
        });
        myQuestionPanel.add(text);
        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();
    }
    /**
     * Displays the provided question in the GUI.
     * The display format is determined by the question type (e.g., multiple-choice, true/false, short answer).
     *
     * @param theQuestion The question to be displayed.
     */
    public void displayQuestion(Question theQuestion) {
        if (theQuestion == null) {
            clearQuestion();
            return;
        }
        myCurrentQuestion = theQuestion;
        myTextArea.setText(theQuestion.getQuestionText());

        myQuestionPanel.removeAll();
        myQuestionPanel.add(myTextArea);

        switch (theQuestion.getQuestionType()) {
            case "MULTICHOICE" -> multiQuestionDisplay(((MultichoiceQuestion) theQuestion).getPossibleAnswers());
            case "SHORTANSWER" -> shortAnswerQuestionDisplay();
            case "TRUEFALSE" -> trueFalseQuestionDisplay();
        }

        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();
    }
    /**
     * Processes if the user's answer is correct and displays the result in a message dialog.
     */
    private void processAnswer() {
        if (myCurrentQuestion != null) {
            boolean isCorrect = myCurrentQuestion.answerIsCorrect(myAnswer);
            if (isCorrect) {
                JOptionPane.showMessageDialog(myQuestionPanel, "Correct!", "Answer Check", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(myQuestionPanel, "Incorrect. The correct answer is: " + myCurrentQuestion.getCorrectAnswer(), "Answer Check", JOptionPane.ERROR_MESSAGE);
            }
            myPCS.firePropertyChange("question_answered", null, isCorrect);
            clearQuestion();
        }
    }

    /**
     * Clears the question panel.
     */
    public void clearQuestion() {
        myCurrentQuestion = null;
        myQuestionPanel.removeAll();
        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();
    }
    /**
     * Adds a PropertyChangeListener to listen for property changes such as question submission.
     *
     * @param theListener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
    /**
     * Handles property change events, such as when a question is sent.
     *
     * @param theEvent The event that triggered the property change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if ("question_sent".equals(theEvent.getPropertyName())) {
            displayQuestion((Question) theEvent.getNewValue());
        }
    }
}
