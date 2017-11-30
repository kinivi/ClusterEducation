package com.projects.deus_ex_machina.clustereducation;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nikita Kiselov on 28-Nov-17.
 * ClusterEducation
 */

public class FirebaseGetter implements DatabaseGetterInterface {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference()
            .child("polls/5a193a33");

    @Override
    public ArrayList<Integer> getValuesOfQuestion(int numberOfQuestion) {



        DatabaseReference values = mDatabase.child("Question" + numberOfQuestion + "/CountOfAnswers");
        values.orderByValue().limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAGGGGGGGGGG", String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        return null;
    }

    @Override
    public ArrayList<Integer> getAnswersTextOfQuestion(int numberOfQuestion) {
        return null;
    }
}
