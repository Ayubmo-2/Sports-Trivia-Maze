/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

/**
 * The MultichoiceQuestion class represents a multiple-choice question in the trivia game.
 * It extends the Question class and includes a list of possible answers.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class MultichoiceQuestion extends Question {

    /**
     * An array of possible answers for the multiple-choice question.
     */
    private final String[] myPossibleAnswers;

    /**
     * Constructs a MultichoiceQuestion with the specified question text, correct answer, and possible answers.
     *
     * @param theQuestion The text of the question.
     * @param theAnswer The correct answer to the question.
     * @param thePossibleAnswers An array of possible answers from which the player can choose.
     */
    public MultichoiceQuestion(String theQuestion, String theAnswer, String[] thePossibleAnswers) {
        super(theQuestion, theAnswer, "MULTICHOICE");
        myPossibleAnswers = thePossibleAnswers;
    }

    /**
     * Returns the array of possible answers for the multiple-choice question.
     *
     * @return A String array of possible answers.
     */
    public String[] getPossibleAnswers() {
        return myPossibleAnswers;
    }
}