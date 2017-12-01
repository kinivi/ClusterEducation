package com.projects.deus_ex_machina.clustereducation;

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


    public void GetData(ValueEventListener vel) {
        DatabaseReference values = mDatabase.child("Question1"
                + "/CountOfAnswers");
        values.orderByValue().addValueEventListener(vel);
    }



    @Override
    public ArrayList<Integer> getValuesOfQuestion(int numberOfQuestion) {

        DatabaseReference values = mDatabase.child("Question" + numberOfQuestion
                + "/CountOfAnswers");

        final ArrayList<Integer> list = new ArrayList<Integer>();


        return null;


    }

    @Override
    public ArrayList<Integer> getAnswersTextOfQuestion(int numberOfQuestion) {
        return null;
    }
}




