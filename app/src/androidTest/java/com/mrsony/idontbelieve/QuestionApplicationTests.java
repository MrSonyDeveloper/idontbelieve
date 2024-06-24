package com.mrsony.idontbelieve;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.longClickOn;
import static com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class QuestionApplicationTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void basicScenario() {

        boolean[] answers = new boolean[] {false,true,true,true};

        assertDisplayed(R.string.hello_text);
        sleep(4000);
        clickOn(R.id.helloView);

        int i = 1;
        for (boolean answ : answers) {
            checkQuestion("Утверждение #" + i, answ);
            i++;
        }
        assertDisplayed(R.id.gameResultView);
        assertDisplayed(R.id.resultTv);
        assertContains("Вы прошли игру без единой ошибки");

        assertDisplayed(R.id.gameResultView);
        sleep(4000);
    }

    private void checkQuestion(String text, boolean answer) {
        sleep(4000);
        assertContains(text);
        longClickOn(answer ? R.id.believe_btn : R.id.dont_believe_btn);
        assertDisplayed(R.string.right);
        sleep(3600);
    }
}