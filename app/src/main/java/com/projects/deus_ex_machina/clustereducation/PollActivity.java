package com.projects.deus_ex_machina.clustereducation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class PollActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        final Context context = PollActivity.this;


        final Button button = findViewById(R.id.button_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //mDatabase.child("test").setValue("WWW" + Math.random());

                mDatabase.child("test/value").runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {

                        Integer currentValue = mutableData.getValue(Integer.class);
                        if (currentValue == null) {
                            mutableData.setValue(1);
                        } else {
                            mutableData.setValue(currentValue + 1);
                        }

                        return Transaction.success(mutableData);

//                        if (p == null) {
//                            Log.d("[][][][]", "BAD");
//                            return Transaction.success(mutableData);
//                        }

//                        if (p.stars.containsKey(getUid())) {
//                            // Unstar the post and remove self from stars
//                            p.starCount = p.starCount - 1;
//                            p.stars.remove(getUid());
//                        } else {
//                            // Star the post and add self to stars
//                            p.starCount = p.starCount + 1;
//                            p.stars.put(getUid(), true);
//                        }

                        // Set value and report transaction success
                        //mutableData.setValue(p);
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

