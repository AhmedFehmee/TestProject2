package com.fahmy.testproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.fahmy.testproject.R;
import com.fahmy.testproject.TestComponentRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private final TestComponentRule component = new TestComponentRule(InstrumentationRegistry.getInstrumentation().getTargetContext());

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class, true, false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Before
    public void init() {
    }

    @Test
    public void intent() {
        Intent intent = new Intent();
        intent.putExtra("your_key", "your_value");

        main.launchActivity(intent);

        // Continue with your test
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.fahmy.testproject", appContext.getPackageName());
    }

    @Test
    public void checkViewsDisplay() {

        Intent intent = new Intent();
        main.launchActivity(intent);

        try {

            Thread.sleep(10000L);

            onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));

            main.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));

            Thread.sleep(10000L);

            onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}