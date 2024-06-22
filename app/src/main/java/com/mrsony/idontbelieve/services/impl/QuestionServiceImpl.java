package com.mrsony.idontbelieve.services.impl;

import com.mrsony.idontbelieve.models.Question;
import com.mrsony.idontbelieve.services.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private static final List<Question> questionsList =
            List.of(
                    new Question(
                            1,
                            "Луна круглая и всегда выглядит одинаково на небе",
                            false,
                            "Луна кажется круглой, но на самом деле она сферическая и ее вид меняется из-за фаз луны."
                    ),
                    new Question(
                            2,
                            "Крокодилы не могут высовывать язык изо рта",
                            true,
                            "Крокодилы действительно не могут высовывать язык из-за специальной мембраны, которая держит его на месте."
                    ),
                    new Question(
                            3,
                            "Пчелы видят все цвета, которые видят люди",
                            true,
                            "Пчелы видят ультрафиолетовый свет, которого люди не видят, но не видят красный цвет."
                    ),
                    new Question(
                            4,
                            "Дельфины дают имена друг другу",
                            false, //Здесь указан неверный ответ, правильный ответ - Верю
                            "Исследования показывают, что дельфины используют уникальные звуковые сигналы, чтобы идентифицировать друг друга, что можно считать аналогом имен."
                    )
            );

    @Override
    public List<Question> getQuestions() {
        return questionsList;
    }
}
