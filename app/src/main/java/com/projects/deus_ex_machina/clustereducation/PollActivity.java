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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class PollActivity extends AppCompatActivity {

    private static final int COUNT_OF_CARDS = 2;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        final Context context = PollActivity.this;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final EditText editText1 = findViewById(R.id.comment_1);
        final EditText editText2 = findViewById(R.id.comment_2);


        final RadioGroup group1 = findViewById(R.id.radioGroup1);
        final RadioGroup group2 = findViewById(R.id.radioGroup2);


        final Button button = findViewById(R.id.button_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String comment1 = String.valueOf(editText1.getText());
                String comment2 = String.valueOf(editText2.getText());

                final String value1 = String.valueOf(((RadioButton) findViewById(group1.getCheckedRadioButtonId()))
                        .getText());

                final String value2 = String.valueOf(((RadioButton) findViewById(group2.getCheckedRadioButtonId()))
                        .getText());


                mDatabase.child("polls/5a193a33/EachAnswers").push()
                        .setValue(new PollResult(value1, value2, comment1, comment2));

                mDatabase.child("polls/5a193a33").runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {


                        int counter=0;

                        for (MutableData n :
                                mutableData.child("Question1/NameOfAnswers").getChildren()) {
                            counter++;
                            if (value1.equals(n.getValue(String.class))) {
                                int count = mutableData.child("Question1/CountOfAnswers/Option" + counter)
                                        .getValue(Integer.class);
                                mutableData.child("Question1/CountOfAnswers/Option" + counter).setValue(++count);
                            }
                        }

                        counter=0;

                        for (MutableData n :
                                mutableData.child("Question2/NameOfAnswers").getChildren()) {
                            counter++;
                            if (value2.equals(n.getValue(String.class))) {
                                int count = mutableData.child("Question2/CountOfAnswers/Option" + counter)
                                        .getValue(Integer.class);
                                mutableData.child("Question2/CountOfAnswers/Option" + counter).setValue(++count);
                            }
                        }


                        return Transaction.success(mutableData);
                    }


                    @Override
                    public void onComplete(DatabaseError databaseError, boolean commited, DataSnapshot dataSnapshot) {
                        Log.d(String.valueOf(Log.INFO), commited + "");
                        Log.d("TAG", "postTransaction:onComplete:" + databaseError);
                    }
                });

            }
        });

    }
}

