package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mUserName, mPassword;
    private Button mButton;
    private boolean isRegistered=  false;
     private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("userDetails",MODE_PRIVATE);
        mUserName = (EditText) findViewById(R.id.uname);
        mPassword = (EditText) findViewById(R.id.pass);
        mButton = (Button)findViewById(R.id.login);
        String userName = sharedPref.getString("UserName", "");
        String passWord = sharedPref.getString("Password","");
        int flag = 0;
        if (TextUtils.isEmpty(userName)){
            Log.d("@Value ", userName);
        }
        if (TextUtils.isEmpty(passWord)){
            Log.d("@Value ", passWord);
        }
        /*if(flag==0){
            mButton.setText("Register");
        }else{
            mButton.setText("Login");
            isRegistered = true;
        }*/
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord)){
            mButton.setText("Register");
            Toast.makeText(getApplicationContext(), "Enter a valid username or password !!!", Toast.LENGTH_LONG).show();
        }else {
            mButton.setText("Login");

        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUserName.getText().toString();
                String password = mPassword.getText().toString();

                boolean isValidInput =   validateUsernamePassword(username, password);
                if (!isValidInput){
                    if (!isRegistered){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("UserName",username);
                        editor.putString("Password",password);
                        editor.commit();
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Successful !!!", Toast.LENGTH_LONG).show();
                        mButton.setText("Logout");

                    }



                }

            }
        });
    }


    private boolean validateUsernamePassword(String userName, String password) {
        if (!(TextUtils.isEmpty(userName))&&!(TextUtils.isEmpty(password))){
            Toast.makeText(getApplicationContext(), "Registration Successful !!!\n Kindly Login Yourself...", Toast.LENGTH_LONG).show();
            mButton.setText("Logout!!!");
            isRegistered=true;
            return false;
        }
        else
            Toast.makeText(getApplicationContext(), "UserName or Password is Empty !!!", Toast.LENGTH_LONG).show();
            return true;
    }

}