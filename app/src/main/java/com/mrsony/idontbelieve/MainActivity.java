package com.mrsony.idontbelieve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.mrsony.idontbelieve.activities.GameActivity;

public class MainActivity extends AppCompatActivity {

    public static final String QUESTIONS_COUNT_KEY = "questionsCount";
    public static final String CORRECT_ANSWERS_COUNT_KEY = "correctAnswersCount";

    private static final int COUNT_PLACEHOLDER = -1;


    private final ActivityResultLauncher<Intent> gameActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Integer questionsCount = data.getIntExtra(QUESTIONS_COUNT_KEY, COUNT_PLACEHOLDER);
                        Integer correctAnswersCount = data.getIntExtra(CORRECT_ANSWERS_COUNT_KEY, COUNT_PLACEHOLDER);
                        if (questionsCount == COUNT_PLACEHOLDER || correctAnswersCount == COUNT_PLACEHOLDER) {
                            return;
                        }
                        showGameResult(questionsCount, correctAnswersCount);
                    }
                }
            });

    private View helloView;
    private View gameResultView;
    private TextView resultGameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloView = findViewById(R.id.helloView);
        gameResultView = findViewById(R.id.gameResultView);
        resultGameTv = gameResultView.findViewById(R.id.resultTv);


        helloView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            gameActivityResultLauncher.launch(intent);
        });
    }

    void showGameResult(int questionsCount, int correctAnswersCount) {
        helloView.setVisibility(View.GONE);
        int resultPercent = (correctAnswersCount * 100) / questionsCount;
        if (resultPercent == 100) {
            resultGameTv.setText(getResources().getString(R.string.without_errors_result));
        } else {
            resultGameTv.setText(getResources().getString(R.string.percent_result, resultPercent));
        }
        gameResultView.setVisibility(View.VISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(
                () -> {
                    gameResultView.setVisibility(View.GONE);
                    helloView.setVisibility(View.VISIBLE);
                },
                1500
        );
    }
}