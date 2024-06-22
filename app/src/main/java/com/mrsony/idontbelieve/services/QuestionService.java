package com.mrsony.idontbelieve.services;

import com.mrsony.idontbelieve.models.Question;
import com.mrsony.idontbelieve.services.provider.Service;

import java.util.List;

public interface QuestionService extends Service {

    List<Question> getQuestions();
}
