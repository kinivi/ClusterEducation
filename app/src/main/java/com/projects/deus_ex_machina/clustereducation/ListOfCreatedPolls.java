package com.projects.deus_ex_machina.clustereducation;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListOfCreatedPolls extends Fragment {

    View rootView;
    ListView listView;
    DatabaseReference mDatabaseRef;
    FirebaseListAdapter adapter;

    public ListOfCreatedPolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_list_of_created_polls, container, false);

        FloatingActionButton button = rootView.findViewById(R.id.button_add_new_survey);

        listView = rootView.findViewById(R.id.list_of_polls);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        Query query = mDatabaseRef.child("polls_info").orderByKey();


        FirebaseListOptions<SurveyInfo> options = new FirebaseListOptions.Builder<SurveyInfo>()
                .setLayout(R.layout.card_of_survey)
                .setQuery(query, SurveyInfo.class)
                .build();


        adapter = new FirebaseListAdapter<SurveyInfo>(options) {
            @Override
            protected void populateView(View v, final SurveyInfo model, final int position) {

                String title = model.getTitle();
                String subTitle = model.getIntroMessage();
                int numberOfQuetion = model.getNumberOfQuestions();
                int participants = model.getParticipants();
                String timeOfCreating = model.getTimeOfCreating();
                final ConstraintLayout layout = v.findViewById(R.id.expand_container);
                final Button buttonExpand = ((Button) v.findViewById(R.id.button_expand));

                ((TextView) v.findViewById(R.id.title)).setText(title);
                ((TextView) v.findViewById(R.id.sub_title)).setText(subTitle);

                buttonExpand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(layout.getVisibility() == View.GONE) {
                            layout.setVisibility(View.VISIBLE);
                            buttonExpand.setText("Hide");
                        } else  {
                            layout.setVisibility(View.GONE);
                            buttonExpand.setText("Expand");
                        }

                    }


                });

                //Invisible text
                ((TextView) layout.findViewById(R.id.number_of_question)).setText(String.valueOf(numberOfQuetion));
                ((TextView) layout.findViewById(R.id.participants)).setText(String.valueOf(participants));
                ((TextView) layout.findViewById(R.id.date_of_creating)).setText(String.valueOf(timeOfCreating));


            }
        };

        adapter.startListening();
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        Intent intent = new Intent(rootView.getContext(), BlankContainerActivity.class);
                        intent.putExtra("TypeOfFragment", "ConstructorFragment");
                        startActivity(intent);
                    }
                }, 200);
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        //adapter.stopListening();

    }
}
