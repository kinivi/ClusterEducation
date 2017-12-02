package com.projects.deus_ex_machina.clustereducation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;

public class PollActivity extends AppCompatActivity {

    private static final int COUNT_OF_CARDS = 2;
    private static final int GOOD_RESULT = 12;
    private DatabaseReference mDatabase;


    EditText editText1;
    EditText editText2;

    //Getting references of RadioGroup views
    RadioGroup group1;
    RadioGroup group2;

    //Getting references of Button to process onClickListener
    Button buttonSend;

    //Context for using Toast
    Context context;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        //Getting context
        context = PollActivity.this;

        //Getting instance of Database to use it
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Getting references of EditText views
        editText1 = findViewById(R.id.comment_1);
        editText2 = findViewById(R.id.comment_2);

        //Getting references of RadioGroup views
        group1 = findViewById(R.id.radioGroup1);
        group2 = findViewById(R.id.radioGroup2);

        //Getting references of Button to process onClickListener
        buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener(listenerToSendButton);
    }

    View.OnClickListener listenerToSendButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Getting comments to answers in poll
            String comment1 = String.valueOf(editText1.getText());
            String comment2 = String.valueOf(editText2.getText());

            //Array of answers for each question
            ArrayList<String> valuesData = new ArrayList<String>();

            valuesData.add(String.valueOf(((RadioButton) findViewById(group1.getCheckedRadioButtonId()))
                    .getText()));

            valuesData.add(String.valueOf(((RadioButton) findViewById(group2.getCheckedRadioButtonId()))
                    .getText()));


            //Pushing all answer to Database
            mDatabase.child("polls/5a193a33/EachAnswers").push()
                    .setValue(new PollResult(valuesData.get(0), valuesData.get(1), comment1, comment2));

            //For each question run transaction
            for (int i = 0; i <= 1; i++) {
                mDatabase.child("polls/5a193a33/Question" + (i + 1) + "/CountOfAnswers/" + valuesData.get(i))
                        .runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {

                                int value;

                                try {
                                    value = mutableData.getValue(Integer.class);
                                    mutableData.setValue(++value);
                                } catch (NullPointerException e) {
                                    Log.e("Error in PollActivity", e.getMessage());
                                }


                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                            }
                        });
            }

            //Exit activity and show Toast
            Toast.makeText(context, "Your answer is saved", Toast.LENGTH_SHORT).show();
            setResult(GOOD_RESULT);
            finish();

        }
    };
}



