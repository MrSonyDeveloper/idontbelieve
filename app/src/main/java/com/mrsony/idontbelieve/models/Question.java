package com.mrsony.idontbelieve.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Question {
    private int id;
    private String statement;
    private boolean correctAnswer;
    private String justification;
}
