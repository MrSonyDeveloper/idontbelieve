package com.mrsony.idontbelieve.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mrsony.idontbelieve.R;
import com.mrsony.idontbelieve.models.Question;
import com.mrsony.idontbelieve.services.QuestionService;
import com.mrsony.idontbelieve.services.provider.ServiceProvider;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private final QuestionService questionService = ServiceProvider.getService(QuestionService.class);

    private List<Question> questionList;
    private int question = 0;

    private LinearLayout gameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameLayout = findViewById(R.id.game_view);

        questionList = questionService.getQuestions();


    }
}
