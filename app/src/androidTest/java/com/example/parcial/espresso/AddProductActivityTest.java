package com.example.parcial.espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.parcial.AddProductActivity;
import com.example.parcial.R;
import com.example.parcial.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class AddProductActivityTest {

    @Rule
    public ActivityScenarioRule<AddProductActivity> activityScenarioRule = new ActivityScenarioRule<>(AddProductActivity.class);

    @Before
    public void onBefore() {
        User testUser = new User(100, "TestUser", "123456789");
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), AddProductActivity.class);
        intent.putExtra("user", testUser);
        activityScenarioRule.getScenario().onActivity(activity -> activity.startActivity(intent));
    }

    @Test
    public void testAddProduct1() throws InterruptedException {
        onView(withId(R.id.ti_ProductNameET)).perform(typeText("TestProduct"), closeSoftKeyboard());
        onView(withId(R.id.ti_ProductQuantityET)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.btn_clear)).perform(click());
    }

    @Test
    public void testAddProduct2() throws InterruptedException {
        onView(withId(R.id.ti_ProductNameET)).perform(typeText("TestProduct"), closeSoftKeyboard());
        onView(withId(R.id.ti_ProductPriceET)).perform(click());
    }

    @Test
    public void testAddProduct3() throws InterruptedException {
        onView(withId(R.id.ti_ProductQuantityET)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.ti_ProductPriceET)).perform(click());
    }

    @Test
    public void testAddProduct4() {
        onView(withId(R.id.ti_ProductNameET)).perform(typeText("TestProduct"), closeSoftKeyboard());
        onView(withId(R.id.ti_ProductQuantityET)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.btn_addProduct)).perform(click());
        onView(withText("Producto registrado correctamente")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
        pressBack();
        onView(withId(R.id.ti_ProductPriceET)).check(matches(withText("120")));
        onView(withId(R.id.btn_clear)).perform(click());
    }

}