package com.example.parcial;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parcial.db.DbUsers;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputLayout ti_user, ti_pass;
    String username, password;
    Button btn_login, btn_signIn;
    DbUsers dbUsers;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initListeners();
    }

    private void initComponents() {
        ti_user = findViewById(R.id.ti_user);
        ti_pass = findViewById(R.id.ti_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_signIn = findViewById(R.id.btn_signin);
    }

    private void initListeners() {
        btn_login.setOnClickListener(view -> {
            validateInputs();
            validateLogin();
        });

        btn_signIn.setOnClickListener(view2 -> {
            validateInputs();
            validateSignIn();
        });
    }

    private void validateInputs() {
        username = Objects.requireNonNull(ti_user.getEditText()).getText().toString().trim();
        password = Objects.requireNonNull(ti_pass.getEditText()).getText().toString().trim();

        if (username.isEmpty())
            ti_user.setHelperText(getResources().getString(R.string.enter_the_username));
        else
            ti_user.setHelperText("");

        if (password.isEmpty())
            ti_pass.setHelperText(getResources().getString(R.string.enter_the_password));
        else
            ti_pass.setHelperText("");
    }

    private void validateLogin() {
        if (!username.isEmpty() && !password.isEmpty()) {
            dbUsers = new DbUsers(this);

            if (dbUsers.validateUsername(username)) {
                id = dbUsers.getId(username, password);
                if (id != -1)
                    goToNextActivity();
                else
                    alert(getResources().getString(R.string.wrong_password));
            } else {
                alert(getResources().getString(R.string.wrong_username));
            }
            dbUsers.close();
        }
    }

    private void goToNextActivity() {
        User user = new User(id, username, password);
        Intent intent = new Intent(this, AddProductActivity.class);
        intent.putExtra("user", user);

        Objects.requireNonNull(ti_user.getEditText()).setText("");
        Objects.requireNonNull(ti_pass.getEditText()).setText("");

        startActivity(intent);
    }

    private void validateSignIn() {
        if (!username.isEmpty() && !password.isEmpty()) {
            dbUsers = new DbUsers(this);
            if (!dbUsers.validateUsername(username)) {
                insertToDb();
                alert(getResources().getString(R.string.user_registered));
            } else {
                alert(getResources().getString(R.string.user_already_exists));
            }
            dbUsers.close();
        }
    }

    private void insertToDb() {
        dbUsers = new DbUsers(this);
        dbUsers.addUser(username, password);
        dbUsers.close();
    }

    private void alert(String title) {
        new AlertDialog.Builder(this)
                .setMessage(title)
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                })
                .show();
    }
}