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


//        DatabaseReference values = mDatabase.child("polls/5a193a33/Question1/CountOfAnswers");
//        values.orderByValue().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot data:
//                     dataSnapshot.getChildren()) {
//                    Log.d("TAG", data.getKey() + ": " + data.getValue(Integer.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }

    View.OnClickListener listenerToSendButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Getting comments to answers in poll
            String comment1 = String.valueOf(editText1.getText());
            String comment2 = String.valueOf(editText2.getText());

            final String value1 = String.valueOf(((RadioButton) findViewById(group1.getCheckedRadioButtonId()))
                    .getText());

            final String value2 = String.valueOf(((RadioButton) findViewById(group2.getCheckedRadioButtonId()))
                    .getText());


            //Pushing all answer to Database
            mDatabase.child("polls/5a193a33/EachAnswers").push()
                    .setValue(new PollResult(value1, value2, comment1, comment2));

            //Incrementing overall count of each answer in connection with user's answer
            mDatabase.child("polls/5a193a33").runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {

                    int counter = 0;

                    for (MutableData childValue :
                            mutableData.child("Question1/NameOfAnswers").getChildren()) {
                        counter++;
                        if (value1.equals(childValue.getValue(String.class))) {

                            //Getting count of Answers to increment
                            int count = mutableData.child("Question1/CountOfAnswers/Option" + counter)
                                    .getValue(Integer.class);

                            //Setting new data(increment)
                            mutableData.child("Question1/CountOfAnswers/Option" + counter).setValue(++count);
                        }
                    }

                    counter = 0;

                    for (MutableData n :
                            mutableData.child("Question2/NameOfAnswers").getChildren()) {
                        counter++;
                        if (value2.equals(n.getValue(String.class))) {

                            //Getting count of Answers to increment
                            int count = mutableData.child("Question2/CountOfAnswers/Option" + counter)
                                    .getValue(Integer.class);

                            //Setting new data(increment)
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

            Toast.makeText(context, "Answer is sent", Toast.LENGTH_SHORT).show();
            setResult(GOOD_RESULT);
            finish();

        }
    };
}



