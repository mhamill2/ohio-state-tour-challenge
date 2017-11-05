package osu.mobile_apps.ohiostatetourchallenge;

public class QuestionAnswer {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer questionId;
    private Integer answerId;
    private boolean isCorrect;

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
