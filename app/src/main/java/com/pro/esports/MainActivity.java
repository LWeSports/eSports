package com.pro.esports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Handler handler=new Handler();
        Runnable run=new Runnable() {
            @Override
            public void run() {
                ++i;
                handler.postDelayed(this,2000);
                if(i==2)
                {
                    handler.removeCallbacksAndMessages(null);



                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                    if(isLoggedIn)
                    {
                        Toast.makeText(MainActivity.this, "Already have an account ", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,home.class);
                        startActivity(intent);
                    }else
                    {
                        Intent intent=new Intent(MainActivity.this,signin.class);
                        startActivity(intent);
                    }

                }
            }
        };
        handler.post(run);

    }
}