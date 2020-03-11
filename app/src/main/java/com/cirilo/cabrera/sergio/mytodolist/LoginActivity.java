package com.cirilo.cabrera.sergio.mytodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnKeyListener {

    private EditText edtNickName;
    private EditText edtPassword;
    private Button btnLogin;

    private TextInputLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
    }

    private void initViews() {
        edtNickName = findViewById(R.id.edtext_nickname);
        edtPassword = findViewById(R.id.edtext_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void initListeners() {

        edtNickName.setOnKeyListener(this);
        edtPassword.setOnKeyListener(this);

        edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE && btnLogin.isEnabled()) {
                    doLogin();
                    return true;
                }
                return false;
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void enablebutton(boolean enabled) {
        btnLogin.setBackgroundColor(ContextCompat.getColor(this, enabled ? R.color.colorPrimary : R.color.colorGray));
        btnLogin.setEnabled(enabled);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        boolean enabled = !edtNickName.getText().toString().contains(" ") && edtPassword.getText().toString().trim().length() > 0;
        btnLogin.setEnabled(enabled);
        return false;
    }
}
