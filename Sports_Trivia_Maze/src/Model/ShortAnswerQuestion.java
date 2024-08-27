/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

/**
 * The ShortAnswerQuestion class represents a short-answer question in the trivia game.
 * It extends the Question class.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public class ShortAnswerQuestion extends Question{


    /**
     * Constructs a ShortAnswerQuestion with the specified question text and correct answer
     *
     * @param theQuestion The text of the question.
     * @param theAnswer The correct answer to the question.
     */
    public ShortAnswerQuestion(String theQuestion, String theAnswer) {
        super(theQuestion, theAnswer, "SHORTANSWER");
    }
}