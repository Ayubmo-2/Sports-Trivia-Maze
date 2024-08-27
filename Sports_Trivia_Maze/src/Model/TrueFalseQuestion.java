/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

/**
 * The TrueFalseQuestion class represents a true-false question in the trivia game.
 * It extends the Question class.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class TrueFalseQuestion extends Question{

    /**
     * Constructs a TrueFalseQuestion with the specified question text and correct answer
     *
     * @param theQuestion The text of the question.
     * @param theAnswer The correct answer to the question.
     */
    public TrueFalseQuestion(String theQuestion, String theAnswer) {
        super(theQuestion, theAnswer, "TRUEFALSE");
    }
}