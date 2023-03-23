package com.example.parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.parcial.db.DbProducts;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {

    TextInputLayout ti_name, ti_quantity, ti_price;
    Button btn_addProduct, btn_clear;
    String product_name, product_quantity;
    int product_price;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initComponents();
        initListeners();
        getUserObject();
    }

    private void initComponents() {
        ti_name = findViewById(R.id.ti_ProductName);
        ti_quantity = findViewById(R.id.ti_ProductQuantity);
        ti_price = findViewById(R.id.ti_ProductPrice);
        btn_addProduct = findViewById(R.id.btn_addProduct);
        btn_clear = findViewById(R.id.btn_clear);
    }

    private void initListeners() {
        btn_addProduct.setOnClickListener(view1 -> validateInputs());
        btn_clear.setOnClickListener(view2 -> clearInpust());
    }

    private void clearInpust() {
        Objects.requireNonNull(ti_name.getEditText()).setText("");
        Objects.requireNonNull(ti_quantity.getEditText()).setText("");
        Objects.requireNonNull(ti_price.getEditText()).setText("");
    }

    private void getUserObject() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
    }

    private void validateInputs() {
        product_name = Objects.requireNonNull(ti_name.getEditText()).getText().toString().trim();
        product_quantity = Objects.requireNonNull(ti_quantity.getEditText()).getText().toString().trim();

        if (product_name.isEmpty())
            ti_name.setHelperText(getResources().getString(R.string.enter_the_product_name));
        else
            ti_name.setHelperText("");

        if (product_quantity.isEmpty())
            ti_quantity.setHelperText(getResources().getString(R.string.enter_the_product_quantity));
        else {
            ti_quantity.setHelperText("");
            PriceCalculator priceCalculator = new PriceCalculator(Integer.parseInt(product_quantity));
            priceCalculator.start();

            try {
                priceCalculator.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            product_price = priceCalculator.getResult();
            Objects.requireNonNull(ti_price.getEditText()).setText(String.valueOf(product_price));
        }

        if (!product_name.isEmpty() && !product_quantity.isEmpty()) {
            insertToDb();
            hideKeyboard();
            alert();
        }
    }

    private void insertToDb() {
        DbProducts dbProducts = new DbProducts(this);
        dbProducts.addProduct(user.getId(), product_name, Integer.parseInt(product_quantity), String.valueOf(product_price));
        dbProducts.close();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btn_addProduct.getWindowToken(), 0);
    }

    private void alert() {
        new AlertDialog.Builder(this)
                .setMessage(getResources().getString(R.string.product_successfully_registered))
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                })
                .setCancelable(false)
                .show();
    }
}