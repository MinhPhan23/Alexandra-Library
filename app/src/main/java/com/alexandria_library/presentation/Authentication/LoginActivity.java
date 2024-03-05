package com.alexandria_library.presentation.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.application.Main;
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
    private EditText userName, password;
    private IAuthentication authentication;
    private static SideBarService sideBarService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        find();
        authentication = new Authentication();
        copyDatabaseToDevice();

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
    }
    public String getTest(){
        return ":shdfsd";
    }
    private void setErrorMess(EditText layout, String message){
        layout.setError(message);
    }


    /******
     * Copy database to device at login page, cause login page is first page landed
     */
    private void copyDatabaseToDevice(){
        final String DB_Path = "db";
        String [] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_Path, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try{
            assetNames = assetManager.list(DB_Path);
            for(int i = 0; i<assetNames.length; i++){
                assetNames[i] = DB_Path + "/" + assetNames[i];
            }
            copyAssetsToDirectory(assetNames, dataDirectory);
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch(final IOException e){
            System.out.println("error in copy database to device");
            e.printStackTrace();
        }
    }

    public void copyAssetsToDirectory(String [] assets, File directory) throws IOException{
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}