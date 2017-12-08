package com.projects.deus_ex_machina.clustereducation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        new Handler().postDelayed(new Runnable() {

            public void run() {
                final Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                final Intent chooserIntent = new Intent(SplashActivity.this, ChooserActivity.class);

                if (mFirebaseUser == null) {
                    // Not signed in, launch the Sign In activity
                    SplashActivity.this.startActivity(chooserIntent);
                    SplashActivity.this.finish();
                } else {
                    //Start MainActivity
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }
        }, 10);


    }
}
