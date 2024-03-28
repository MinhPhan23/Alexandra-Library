package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.data.utils.HSQLDBHelper;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.Exception.AuthenticationException;
import com.alexandria_library.logic.IAuthentication;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private Button login, register;
    private EditText userName, password;
    private IAuthentication authentication;
    private static SideBarService sideBarService;

    /////////////////////////LIBRARIAN MODE UI/////////////////////////
    private Button librarianModeBtn, userModeBtn;
    private boolean librarianMode;
    private FrameLayout library_mode_windows, reader_mode_windows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        copyDatabaseToDevice(); //copy database

        Bundle crossActivityVariables = getIntent().getExtras();
        if(crossActivityVariables != null){
            librarianMode = crossActivityVariables.getBoolean("librarianMode");
        }
        else{
            librarianMode = false;
        }

        find();
        updateModeBtns();//updates the button state to communicate if in librarian mode
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

    private void loginBtnClicked(View v){
        String name = userName.getText().toString();
        String pw = password.getText().toString();
        try {
            User user = authentication.login(name, pw, librarianMode);
            sideBarService = new SideBarService(user);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("librarianMode", librarianMode);//passes librarianMode state
            startActivity(i);
        }
        catch (AuthenticationException e) {
            setErrorMess(password, e.getMessage());
        }
    }
    private void registerBtnClicked(View v){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        i.putExtra("librarianMode", librarianMode);//passes librarianMode state
        startActivity(i);
    }

    public static SideBarService getSideBarService(){
        return sideBarService;
    }

    private void find(){
        login = findViewById(R.id.login_btn);
        register = findViewById(R.id.register_btn);
        userName = findViewById(R.id.login_userName_input);
        password = findViewById(R.id.login_password_input);

        ////////////////LIBRARIAN MODE UI////////////////////
        librarianModeBtn = findViewById(R.id.librarian_mode_btn);
        userModeBtn = findViewById(R.id.user_mode_btn);

        library_mode_windows = findViewById(R.id.librarian_mode_window);
        reader_mode_windows  = findViewById(R.id.reader_mode_window);
    }

    private void setErrorMess(EditText layout, String message){
        layout.setError(message);
    }

    private void copyDatabaseToDevice() {
        HSQLDBHelper.copyDatabaseToDevice(this);
    }

    ///////////////////////////LIBRARIAN MODE////////////////////////

    /******
     * depending on the librarianMode state highlights the text of the buttons to communicate
     * to the user which mode is selected
     */
    private void updateModeBtns() {
        if (librarianMode) {
            library_mode_windows.setBackground(ContextCompat.getDrawable(this, R.drawable.clicked_btn_bacc));
            reader_mode_windows.setBackground(ContextCompat.getDrawable(this, R.drawable.login_button));
        } else {
            library_mode_windows.setBackground(ContextCompat.getDrawable(this, R.drawable.login_button));
            reader_mode_windows.setBackground(ContextCompat.getDrawable(this, R.drawable.clicked_btn_bacc));
        }
        // Assuming library_mode_windows and reader_mode_windows need to be invalidated to reflect changes.
        library_mode_windows.invalidate();
        reader_mode_windows.invalidate();
    }
}