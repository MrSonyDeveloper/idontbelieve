package com.mrsony.idontbelieve;

import static com.adevinta.android.barista.assertion.BaristaContentDescriptionAssertions.assertContentDescription;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.longClickOn;
import static com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep;
import static com.google.android.apps.common.testing.accessibility.framework.strings.StringManager.getString;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
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
            Question("Утверждение #" + i, answ);
            i++;
        }
        assertDisplayed(R.id.resultTv);
        assertDisplayed("Вы прошли игру без единой ошибки");

        assertDisplayed(R.id.gameResultView);
        sleep(4000);
    }

    public void Question(String text, boolean answer) {
        sleep(4000);
        assertDisplayed(text);
        longClickOn(answer ? R.id.believe_btn : R.id.dont_believe_btn);
        assertDisplayed(R.string.right);
        sleep(3600);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.mrsony.idontbelieve", appContext.getPackageName());
    }
}