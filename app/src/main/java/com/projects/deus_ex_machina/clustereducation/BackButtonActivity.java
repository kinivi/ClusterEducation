package com.projects.deus_ex_machina.clustereducation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class BackButtonActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_button);

        Intent mIntent = this.getIntent();

        if(mIntent.getStringExtra("TypeOfFragment").equals("PollFragment")) {
            Fragment mFragment = new PollFragment();

            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dynamic_backbutton_container, mFragment).commit();
        } else if(mIntent.getStringExtra("TypeOfFragment").equals("FeedbackFragment")) {
            getSupportFragmentManager().beginTransaction()
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.dynamic_backbutton_container, new FeedbackFragment()).commit();
        }




    }
}



