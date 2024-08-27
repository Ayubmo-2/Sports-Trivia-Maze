package tests;

import Model.MultichoiceQuestion;
import Model.Question;
import Model.ShortAnswerQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTest {
    private Question myQuestion;
    @BeforeEach
    void setUp() {
        myQuestion = new ShortAnswerQuestion("test", "test");
        String[] answers = {"Answer1","Answer2","Answer3","Answer4"};
        Question quest = new MultichoiceQuestion("Question", "Answer", answers);
    }

    @Test
    void getQuestionType() {
        assertEquals(myQuestion.getQuestionType(), "SHORTANSWER");
    }

    @Test
    void answerIsCorrect() {
        assertTrue(myQuestion.answerIsCorrect("test"));
    }

    @Test
    void getCorrectAnswer() {
        assertEquals(myQuestion.getCorrectAnswer(), "test");
    }

    @Test
    void getPossibleAnswers() {
        String[] answers = {"Answer1","Answer2","Answer3","Answer4"};
        MultichoiceQuestion quest = new MultichoiceQuestion("Question", "Answer", answers);
        assertEquals(quest.getPossibleAnswers(), answers);
    }

    @Test
    void getQuestionText() {
        assertEquals(myQuestion.getQuestionText(), "test");
    }
}