package com.projects.deus_ex_machina.clustereducation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        TextView entrant = (TextView) findViewById(R.id.entrant);
        TextView StudentOrMentor = (TextView) findViewById(R.id.student_mentor);

        entrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Add here Toast message to inform people, that this feature is developing
            }
        });

        StudentOrMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooserActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
