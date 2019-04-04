package com.example.ttt0306projectdigitalclock;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasTextColor;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.ttt0306projectdigitalclock.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

/*
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.ttt0306projectdigitalclock", appContext.getPackageName());
    }
*/

    //invokes the MainActity for testing
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateUserWithCorrectCredential() throws InterruptedException {


        // MAIN PAGE
        // go to settings
        onView(withId(R.id.menu_settings)).perform(click());

        // SETTINGS PAGE
        onView(withId(R.id.radoBlue)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.listTimeZones)).perform(click());

        //onView(allOf(withId(R.id.listTimeZones),  ("Pacific"))).perform(click());
        //onView(withId(R.id.listTimeZones)).clickInList(clickPo());
        //onView(withId(R.id.listTimeZones)).perform(click());
        //onView(withId(R.id.listTimeZones)).perform(click());
        //onView(withId(R.id.listTimeZones)).perform(click());
        //listLiew.performItemClick(listLiew, POSTITION_IN_LIST, listLiew.getItemIdAtPosition(POSTITION_IN_LIST));


        // go to main
        onView(withId(R.id.menu_settings)).perform(click());

        // MAIN PAGE
        //check color
        onView(withId(R.id.idSecondsTop)).check(matches(hasTextColor(R.color.colorBlue)));
        Thread.sleep(1000);

        // go to settings
        onView(withId(R.id.menu_settings)).perform(click());

        // SETTINGS PAGE
        onView(withId(R.id.radoOrange)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Pacific (PST)"))).perform(click());
        Thread.sleep(1000);

        // go to main
        onView(withId(R.id.menu_settings)).perform(click());

        // MAIN PAGE
        //check color
        onView(withId(R.id.idSecondsTop)).check(matches(hasTextColor(R.color.colorOrange)));
        Thread.sleep(1000);
/*
        onView(withId(R.id.idSecondsTop)).check(matches(hasTextColor(R.color.colorLightBlue)));
        onView(withId(R.id.idSecondsTop)).check(matches(hasTextColor(R.color.colorBlue)));
*/

        // swipe for new image
        //onView(withId(R.id.idPage)).perform(swipeLeft());

        onView(withId(R.id.idPage)).perform(swipeRight());

        Thread.sleep(1000);

        //check image
        onView(withId(R.id.idImage)).check(matches(withDrawable(android.R.drawable.btn_star_big_on)));

        //onData(mActivityRule.getActivity().intImage).check(matches(1));


        Thread.sleep(1000);

/*
        viewImage.setImageResource(android.R.drawable.btn_star);
        viewImage.setImageResource(android.R.drawable.btn_star_big_on);
        viewImage.setImageResource(android.R.drawable.ic_popup_reminder);
        viewImage.setImageResource(android.R.drawable.ic_btn_speak_now);
*/


/*
        onView(withId(R.id.card_number)).perform(typeText("5000"));
        onView(withId(R.id.card_type_indicator)).check(matches(withDrawable(R.drawable.mastercard)));
        onView(withId(R.id.card_number)).perform(clearText());
        onView(withId(R.id.card_number)).perform(typeText("4000"));
        onView(withId(R.id.card_type_indicator)).check(matches(withDrawable(R.drawable.visa)));
        onView(withId(R.id.card_number)).perform(clearText());
        onView(withId(R.id.card_number)).perform(typeText("3400"));
        onView(withId(R.id.card_type_indicator)).check(matches(withDrawable(R.drawable.amex)));
        onView(withId(R.id.card_number)).perform(clearText());
        onView(withId(R.id.card_number)).perform(typeText("0000"));
        onView(withId(R.id.card_type_indicator)).check(matches(noDrawable()));
*/


/*
        // MAIN PAGE
        onView(withId(R.id.textViewWelcome)).check(matches());
        Thread.sleep(2000);
*/


/*
        onView(withId(R.id.editTextPassword)).perform(typeText("admin password"), closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.textViewWelcome)).check(matches(withText("Welcome admin")));
*/

    }


}
