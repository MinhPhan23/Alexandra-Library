package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.alexandria_library.R;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName, password, doubleCheckPW;
    private Button goRegiste;
    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        find();
        authentication = new Authentication();
        goRegiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                else if(authentication.findExits(name, pw) != null){
                    return;
                }
                else{
                    authentication.insertNewUser(name, pw);
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    void find(){
        userName = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        doubleCheckPW = findViewById(R.id.register_confirm_password_input);
        goRegiste = findViewById(R.id.Create_register_btn);
    }
}