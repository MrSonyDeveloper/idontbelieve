package com.mrsony.idontbelieve.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private Question currentQuestion;

    private LinearLayout gameLayout;
    private View currentQuestionRow;
    private View positiveBadge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameLayout = findViewById(R.id.game_view);
        positiveBadge = findViewById(R.id.badge_area);

        Button believeButton = findViewById(R.id.believe_btn);

        believeButton.setOnClickListener(this::onBelieveButtonClick);

        questionList = questionService.getQuestions();

        currentQuestion = questionList.get(0);
        currentQuestionRow = getLayoutFromQuestion(currentQuestion);

        gameLayout.addView(currentQuestionRow);
    }

    private View getLayoutFromQuestion(Question question) {
        View questionView = getLayoutInflater().inflate(R.layout.question_row, gameLayout, false);

        TextView questionNumberTextView = questionView.findViewById(R.id.question_number);
        questionNumberTextView.setText(getResources().getString(R.string.questionNumber, question.getId()));

        TextView questionTv = questionView.findViewById(R.id.question_statement);
        questionTv.setText(question.getStatement());
        return questionView;
    }

    private void onBelieveButtonClick(View view) {
        gameLayout.removeView(currentQuestionRow);
        positiveBadge.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            positiveBadge.setVisibility(View.GONE);
            currentQuestionRow = getLayoutFromQuestion(questionList.get(1));
            gameLayout.addView(currentQuestionRow);
        }, 3000);
    }
}
