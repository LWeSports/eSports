package com.pro.esports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class signin extends AppCompatActivity {

    private static final String EMAIL = "email";
    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile", "user_friends");

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn)
        {
            Toast.makeText(this, "Already have an account ", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(signin.this,home.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(this, "Didn't have account", Toast.LENGTH_SHORT).show();
        }

        //  loginButton.setReadPermissions(Arrays.asList("email","public_profile"));


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(signin.this, "Loged in successfully ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(signin.this,home.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(signin.this, "Loged in faild ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(signin.this, ""+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        // If you are using in a fragment, call loginButton.setFragment(this);



        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null)
            {
                Toast.makeText(signin.this, "User loged out", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(signin.this, "User Active", Toast.LENGTH_SHORT).show();
            }
        }
    };

}