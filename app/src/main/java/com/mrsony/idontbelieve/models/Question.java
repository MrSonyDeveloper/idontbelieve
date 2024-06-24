package com.mrsony.idontbelieve.models;

public class Question {
    private final int id;
    private final String statement;
    private final boolean correctAnswer;
    private final String justification;

    public Question(int id, String statement, boolean correctAnswer, String justification) {
        this.id = id;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.justification = justification;
    }

    public int getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public String getJustification() {
        return justification;
    }
}
