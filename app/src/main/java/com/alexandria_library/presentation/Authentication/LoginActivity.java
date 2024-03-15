package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.application.Main;
import com.alexandria_library.data.utils.HSQLDBHelper;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.AuthenticationException;
import com.alexandria_library.logic.IAuthentication;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.MainActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {
    private Button login, register;

    private Button librarianModeBtn, userModeBtn;
    private boolean librarianMode;
    private EditText userName, password;
    private IAuthentication authentication;
    private static SideBarService sideBarService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        copyDatabaseToDevice(); //copy database
        librarianMode = false;
        find();
        authentication = new Authentication();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtnClicked(v);
            }
        });

        /*******
         * also can use "Enter" keyword to instand of click button
         */
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                    login.performClick();

                    return true;
                }
                return false;
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtnClicked(v);
            }
        });

        librarianModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!librarianMode){
                    librarianModeBtn.setTextColor(Color.parseColor("#321450"));
                    userModeBtn.setTextColor(Color.parseColor("#FFFFFF"));
                    librarianMode = true;
                    librarianModeBtn.invalidate();
                    userModeBtn.invalidate();
                }
            }
        });

        userModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(librarianMode){
                    librarianModeBtn.setTextColor(Color.parseColor("#FFFFFF"));
                    userModeBtn.setTextColor(Color.parseColor("#321450"));
                    librarianMode = false;
                    librarianModeBtn.invalidate();
                    userModeBtn.invalidate();
                }
            }
        });
    }

    private void loginBtnClicked(View v){
        String name = userName.getText().toString();
        String pw = password.getText().toString();
        try {
            User user = authentication.login(name, pw);
            sideBarService = new SideBarService(user);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        catch (AuthenticationException e) {
            setErrorMess(password, e.getMessage());
        }
    }
    private void registerBtnClicked(View v){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    public static SideBarService getSideBarService(){
        return sideBarService;
    }

    private void find(){
        login = findViewById(R.id.login_Create_register_btn);
        register = findViewById(R.id.register_btn);
        userName = findViewById(R.id.login_userName_input);
        password = findViewById(R.id.login_password_input);
        librarianModeBtn = findViewById(R.id.librarian_mode_btn);
        userModeBtn = findViewById(R.id.user_mode_btn);
    }

    private void setErrorMess(EditText layout, String message){
        layout.setError(message);
    }

    private void copyDatabaseToDevice() {
        HSQLDBHelper.copyDatabaseToDevice(this);
    }
}