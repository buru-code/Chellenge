package com.demo.delta.deltapost;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.demo.delta.deltapost.view.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivity = new ActivityScenarioRule<>(MainActivity.class);


   //Only for demonstration purposes if you would like to validate a particular data
    @Test
    public void recyclerViewScrollTest(){
        onView(withId(R.id.post_list))
            .perform(RecyclerViewActions.scrollToPosition(1));

        onView(withText("example"))
            .check(matches(isDisplayed()));
    }

}
