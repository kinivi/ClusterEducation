package com.projects.deus_ex_machina.clustereducation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PollFragment extends Fragment {

    private View rootView;
    private static final int GOOD_RESULT = 12;
    private DatabaseReference mDatabase;

    //Edit text values
    EditText editText1;
    EditText editText2;

    //RadioGroup views values
    RadioGroup group1;
    RadioGroup group2;

    //Getting references of Button to process onClickListener
    Button buttonSend;

    //Context for using Toast
    Context context;

    public PollFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_poll, container, false);

        //Getting context
        context = rootView.getContext();

        //Getting instance of Database to use it
        mDatabase = FirebaseDatabase.getInstance().getReference().child("polls/5a193a33");

        //Getting references of EditText views
        editText1 = rootView.findViewById(R.id.comment_1);
        editText2 = rootView.findViewById(R.id.comment_2);

        //Getting references of RadioGroup views
        group1 = rootView.findViewById(R.id.type_of_answers);
        group2 = rootView.findViewById(R.id.radioGroup2);

        //Getting references of Button to process onClickListener
        buttonSend = rootView.findViewById(R.id.button_send);

        buttonSend.setOnClickListener(listenerToSendButton);

        return rootView;
    }

    View.OnClickListener listenerToSendButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Getting comments to answers in poll
            String comment1 = String.valueOf(editText1.getText());
            String comment2 = String.valueOf(editText2.getText());

            //Array of answers for each question
            ArrayList<String> valuesData = new ArrayList<String>();

            valuesData.add(String.valueOf(((RadioButton) rootView.findViewById(group1.getCheckedRadioButtonId()))
                    .getText()));

            valuesData.add(String.valueOf(((RadioButton) rootView.findViewById(group2.getCheckedRadioButtonId()))
                    .getText()));


            //Pushing all answer to Database
            mDatabase.child("EachAnswers").push()
                    .setValue(new PollResult(valuesData.get(0), valuesData.get(1), comment1, comment2));

            //For each question run transaction
            for (int i = 0; i <= 1; i++) {
                mDatabase.child("Survey" + (i + 1) + "/CountOfAnswers/" + valuesData.get(i))
                        .runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {

                                int value;

                                try {
                                    value = mutableData.getValue(Integer.class);
                                    mutableData.setValue(++value);
                                } catch (NullPointerException e) {
                                    Log.e("Error BackButtonActiv", e.getMessage());
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
            getActivity().setResult(GOOD_RESULT);
            getActivity().finish();

        }
    };
}
