package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.Exception.AuthenticationException;
import com.alexandria_library.logic.IAuthentication;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName, password, doubleCheckPW;
    private Button goRegister;
    private IAuthentication authentication;

    /////////////////////////LIBRARIAN MODE UI/////////////////////////
    private Button librarianModeBtn, userModeBtn;
    private boolean librarianMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle crossActivityVariables = getIntent().getExtras();
        if(crossActivityVariables != null){//accepts librarianMode state if passed, defaults to false
            librarianMode = crossActivityVariables.getBoolean("librarianMode");
        }
        else{
            librarianMode = false;
        }

        find();
        updateModeBtns();//updates the button state to communicate if in librarian mode
        authentication = new Authentication();
        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtnClicked(v);
            }
        });

        //also can use "enter" keyword to instead of click register button
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

        ///////////////////////////LIBRARIAN MODE////////////////////////////

        librarianModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!librarianMode){//switches into librarian mode
                    librarianMode = true;
                    updateModeBtns();
                }
            }
        });

        userModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(librarianMode){//switches out of librarian mode
                    librarianMode = false;
                    updateModeBtns();
                }
            }
        });
    }

    void find(){
        userName = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        doubleCheckPW = findViewById(R.id.register_confirm_password_input);
        goRegister = findViewById(R.id.Create_register_btn);
        librarianModeBtn = findViewById(R.id.librarian_mode_btn);
        userModeBtn = findViewById(R.id.user_mode_btn);
    }

    private void registerBtnClicked(View v){
        String name = userName.getText().toString();
        String pw = password.getText().toString();
        String doublePW = doubleCheckPW.getText().toString();
        try {
            authentication.register(name, pw, doublePW, librarianMode);

            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            i.putExtra("librarianMode", librarianMode);
            startActivity(i);
        }
        catch (AuthenticationException e) {
            setErrorMess(password, e.getMessage());
        }
    }

    private void setErrorMess(EditText layout, String message){
        layout.setError(message);
    }

    ///////////////////////////LIBRARIAN MODE////////////////////////

    /******
     * depending on the librarianMode state highlights the text of the buttons to communicate
     * to the user which mode is selected
     */
    private void updateModeBtns() {
        if(librarianMode){
            librarianModeBtn.setTextColor(Color.parseColor("#FFFFFF"));
            userModeBtn.setTextColor(Color.parseColor("#321450"));
        }
        else{
            librarianModeBtn.setTextColor(Color.parseColor("#000000"));
            userModeBtn.setTextColor(Color.parseColor("#FFFFFF"));
        }
        librarianModeBtn.invalidate();//redraws the buttons with the new text colours
    }
}