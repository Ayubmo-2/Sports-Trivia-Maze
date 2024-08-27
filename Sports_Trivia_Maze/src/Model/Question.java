/*
 * TCSS 360 - Sports Trivia Maze
 */

package Model;

/**
 * The Question class represents a question and its correct answer.
 * It contains the text of the question and a boolean indicating the correct answer.
 *
 * @author Jacob Waite jwaite9@uw.edu, Edison Chen ec1924@uw.edu, Ayub Mohamed ayubmo20@uw.edu
 * @version 16 August 2024
 */
public abstract class Question {

    /**
     * The question being asked.
     */
    private final String myQuestion;

    /**
     * The correct answer to the question.
     */
    private final String myCorrectAnswer;

    /**
     * The question type being asked.
     */
    private final String myQuestionType;

    /**
     * Constructs a Question with the specified text and correct answer.
     *
     * @param theQuestion The text of the question.
     * @param theCorrectAnswer The correct answer to the question.
     */
    public Question(final String theQuestion, final String theCorrectAnswer, String theType) {
        myQuestion = theQuestion;
        myCorrectAnswer = theCorrectAnswer;
        myQuestionType = theType;
    }

    /**
     * Returns the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestionType() {
        return myQuestionType;
    }

    /**
     * Returns the correct answer to the question.
     *
     * @return true if the answer is correct, false otherwise.
     */
    public boolean answerIsCorrect(String theAnswer) {
        return myCorrectAnswer.equalsIgnoreCase(theAnswer);
    }

    /**
     * Returns the correct answer to the question.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() { return myCorrectAnswer; }

    /**
     * Retrieves the text of the question associated with the door.
     *
     * @return A String representing the text of the question.
     */
    public String getQuestionText() {
        return myQuestion;
    }

}
