package com.example.parcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parcial.db.DbUsers;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputLayout ti_user, ti_pass;
    String username, password;
    Button btn_login, btn_signin;

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
        btn_signin = findViewById(R.id.btn_signin);
    }

    private void initListeners() {
        btn_login.setOnClickListener(view1 -> validateInputs());
        btn_signin.setOnClickListener(view2 -> validateInputs2();
    }

    private void validateInputs() {
        username = Objects.requireNonNull(ti_user.getEditText()).getText().toString().trim();
        password = Objects.requireNonNull(ti_pass.getEditText()).getText().toString().trim();

        if (username.isEmpty())
            ti_user.setHelperText(getResources().getString(R.string.enter_the_product_name));
        else
            ti_user.setHelperText("");

        if (password.isEmpty())
            ti_pass.setHelperText(getResources().getString(R.string.enter_the_product_quantity));
        else
            ti_pass.setHelperText("");

        if (!username.isEmpty() && !password.isEmpty()) {
            User user = new User(1,username, password);
            Intent intent = new Intent(this, AddProductActivity.class);
            intent.putExtra("user", user);

            ti_user.getEditText().setText("");
            ti_pass.getEditText().setText("");

            startActivity(intent);
        }

    }

    private void validateInputs2() {
        username = Objects.requireNonNull(ti_user.getEditText()).getText().toString().trim();
        password = Objects.requireNonNull(ti_pass.getEditText()).getText().toString().trim();

        if (username.isEmpty())
            ti_user.setHelperText(getResources().getString(R.string.enter_the_product_name));
        else
            ti_user.setHelperText("");

        if (password.isEmpty())
            ti_pass.setHelperText(getResources().getString(R.string.enter_the_product_quantity));
        else
            ti_pass.setHelperText("");

        if (!username.isEmpty() && !password.isEmpty()) {
            insertToDb();
        }
    }

    private void insertToDb() {
        DbUsers dbUsers = new DbUsers(this);
        dbUsers.addUser(username, password);
    }
}