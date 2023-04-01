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
import static com.example.parcial.db.DbHelper.DATABASE_TABLE_USERS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.parcial.MainActivity;
import com.example.parcial.R;
import com.example.parcial.db.DbUsers;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test1_loginWhenUserIsNotRegistered() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbUsers dbUsers = new DbUsers(context);
        SQLiteDatabase db = dbUsers.getWritableDatabase();

        db.execSQL(" DELETE FROM " + DATABASE_TABLE_USERS);
        db.execSQL(" DELETE FROM sqlite_sequence WHERE name = 'Usuarios'");

        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.txt_EmailL_et)).perform(typeText("User001"), closeSoftKeyboard());
        onView(withId(R.id.txt_PasswordL_et)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        onView(withText("El usuario no está registrado")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
        pressBack();
    }

    @Test
    public void test2_singIn() {
        onView(withId(R.id.txt_EmailL_et)).perform(typeText("User001"), closeSoftKeyboard());
        onView(withId(R.id.txt_PasswordL_et)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.btn_signin)).perform(click());
        onView(withText("Usuario registrado correctamente")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
        pressBack();
    }

    @Test
    public void test3_singInWhenUserIsAlreadyRegistered() {
        onView(withId(R.id.txt_EmailL_et)).perform(typeText("User001"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.txt_PasswordL_et)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.btn_signin)).perform(click());

        onView(withText("El usuario ya se encuentra registrado")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
        pressBack();
    }

    @Test
    public void test4_loginWhenUserEntersWrongPassword() {
        onView(withId(R.id.txt_PasswordL_et)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.txt_EmailL_et)).perform(typeText("User001"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Contraseña incorrecta")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
        pressBack();
    }

    @Test
    public void test5_loginWhenUserIsRegistered() {
        onView(withId(R.id.txt_EmailL_et)).perform(typeText("User001"), closeSoftKeyboard());
        onView(withId(R.id.txt_PasswordL_et)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
    }
}