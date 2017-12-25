package com.projects.deus_ex_machina.clustereducation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import javax.xml.namespace.NamespaceContext;

public class BlankContainerActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_button);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_open_subject, null);

        Intent mIntent = this.getIntent();
        if(mIntent.getStringExtra("TypeOfFragment").equals("PollFragment")) {
            Fragment mFragment = new PollFragment();

            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dynamic_backbutton_container, mFragment).commit();
        } else if(mIntent.getStringExtra("TypeOfFragment").equals("FeedbackFragment")) {
            getSupportFragmentManager().beginTransaction()
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.dynamic_backbutton_container, new FeedbackFragment()).commit();
        } else if(mIntent.getStringExtra("TypeOfFragment").equals("OpenSubjectFragment")) {
            OpenSubjectFragment openSubjectFragment = new OpenSubjectFragment();
            openSubjectFragment.setName_of_a_subject(mIntent.getStringExtra("Name"));
            getSupportFragmentManager().beginTransaction()
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.dynamic_backbutton_container, openSubjectFragment).commit();
        }
    }
}



