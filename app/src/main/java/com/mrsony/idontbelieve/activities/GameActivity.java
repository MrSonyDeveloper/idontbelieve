package com.mrsony.idontbelieve.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsControllerCompat;

import com.mrsony.idontbelieve.MainActivity;
import com.mrsony.idontbelieve.R;
import com.mrsony.idontbelieve.models.Question;
import com.mrsony.idontbelieve.services.QuestionService;
import com.mrsony.idontbelieve.services.provider.ServiceProvider;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private final QuestionService questionService = ServiceProvider.getService(QuestionService.class);

    private List<Question> questionList;

    private Question currentQuestion;
    private int currentQuestionIndex = 0;

    private int correctAnswersCount = 0;

    private LinearLayout gameLayout;
    private View currentQuestionRow;
    private View positiveBadge;
    private View negativeBadge;
    private View buttonView;
    private TextView justificationTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window = getWindow();
        WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(window, window.getDecorView());
        wic.setAppearanceLightStatusBars(false);
        window.setStatusBarColor(getColor(R.color.status_bar_color));

        gameLayout = findViewById(R.id.game_view);
        positiveBadge = findViewById(R.id.positiveBadge);
        negativeBadge = findViewById(R.id.negative_badge);
        buttonView = findViewById(R.id.buttonView);
        justificationTv = negativeBadge.findViewById(R.id.justificationTv);

        Button believeButton = findViewById(R.id.believe_btn);
        believeButton.setOnClickListener(view -> onButtonClick(true));

        Button dontBeliveButton = findViewById(R.id.dont_believe_btn);
        dontBeliveButton.setOnClickListener(view -> onButtonClick(false));

        questionList = questionService.getQuestions();

        currentQuestion = questionList.get(currentQuestionIndex);
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

    private void onButtonClick(boolean answer) {
        gameLayout.removeView(currentQuestionRow);
        buttonView.setVisibility(View.GONE);
        boolean answerResult = currentQuestion.isCorrectAnswer() == answer;
        if (answerResult) {
            positiveBadge.setVisibility(View.VISIBLE);
            correctAnswersCount++;
        } else {
            negativeBadge.setVisibility(View.VISIBLE);
            justificationTv.setText(currentQuestion.getJustification());
        }
        currentQuestionIndex++;

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            positiveBadge.setVisibility(View.GONE);
            negativeBadge.setVisibility(View.GONE);
            if (currentQuestionIndex >= questionList.size()) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.QUESTIONS_COUNT_KEY, questionList.size());
                intent.putExtra(MainActivity.CORRECT_ANSWERS_COUNT_KEY, correctAnswersCount);
                setResult(RESULT_OK, intent);
                finish();
                return;
            }
            currentQuestion = questionList.get(currentQuestionIndex);
            currentQuestionRow = getLayoutFromQuestion(questionList.get(currentQuestionIndex));
            gameLayout.addView(currentQuestionRow);
            buttonView.setVisibility(View.VISIBLE);
        }, answerResult ? 1500 : 3500);
    }
}
