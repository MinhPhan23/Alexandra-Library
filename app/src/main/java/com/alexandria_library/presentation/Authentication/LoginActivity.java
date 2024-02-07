package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alexandria_library.R;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.presentation.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private Button login, register;
    private EditText userName, password;
    private Authentication authentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        find();
        authentication = new Authentication();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pw = password.getText().toString();
                User foundUser = authentication.findExits(name, pw);
                checkLogin(foundUser, name, pw);
            }
            private void checkLogin(User foundUser, String userName, String pw) {
                if(foundUser != null){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else if(userName == null){
                    //setErrorMess(password, "Please enter a user name to log in");
                }
                else if(pw == null){
                    //setErrorMess(password, "Please enter a password to log in");
                }
                else {
                    //setErrorMess(password, "User name or Password is wrong");
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void find(){
        login = findViewById(R.id.login_Create_register_btn);
        register = findViewById(R.id.register_btn);
        userName = findViewById(R.id.login_userName_input);
        password = findViewById(R.id.login_password_input);
    }
    private void setErrorMess(EditText layout, String message){
        layout.setError(message);
    }
}