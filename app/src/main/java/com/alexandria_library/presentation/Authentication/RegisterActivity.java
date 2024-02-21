package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.logic.Authentication;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName, password, doubleCheckPW;
    private Button goRegister;
    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        find();
        authentication = new Authentication();
        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtnClicked(v);
            }
        });
        /******
         * also can use "enter" keyword to instead of click register button
         */
        doubleCheckPW.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                    goRegister.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    void find(){
        userName = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        doubleCheckPW = findViewById(R.id.register_confirm_password_input);
        goRegister = findViewById(R.id.Create_register_btn);
    }

    private void registerBtnClicked(View v){
        String name = userName.getText().toString();
        String pw = password.getText().toString();
        String doublePW = doubleCheckPW.getText().toString();
        checkRegister(name, pw, doublePW);
    }

    private void checkRegister(String name, String pw, String doublePW){
        if(name == null || pw == null || doublePW == null){
            return;
        }
        else if (!pw.equals(doublePW)){
            return;
        }
        else if(authentication.findExist(name, pw) != null){
            return;
        }
        else{
            authentication.insertNewUser(name, pw);
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }
}