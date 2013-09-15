
package com.bbisercic.ort1.database.dao.beans;

public class QuizBean {

    private long mId;

    private String mQuestion;

    private String mAnswer;

    public QuizBean(long mId, String mQuestion, String mAnswer) {
        this.mId = mId;
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
    }

    public QuizBean(String mQuestion, String mAnswer) {
        this(0, mQuestion, mAnswer);
    }

    public QuizBean() {
        this(0, "n/a", "n/a");
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    @Override
    public String toString() {
        return "QuizBean [mId=" + mId + ", mQuestion=" + mQuestion + ", mAnswer=" + mAnswer + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mAnswer == null) ? 0 : mAnswer.hashCode());
        result = prime * result + (int) (mId ^ (mId >>> 32));
        result = prime * result + ((mQuestion == null) ? 0 : mQuestion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuizBean other = (QuizBean) obj;
        if (mAnswer == null) {
            if (other.mAnswer != null)
                return false;
        } else if (!mAnswer.equals(other.mAnswer))
            return false;
        if (mId != other.mId)
            return false;
        if (mQuestion == null) {
            if (other.mQuestion != null)
                return false;
        } else if (!mQuestion.equals(other.mQuestion))
            return false;
        return true;
    }

}
