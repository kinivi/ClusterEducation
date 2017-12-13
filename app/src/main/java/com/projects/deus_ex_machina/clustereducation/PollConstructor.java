package com.projects.deus_ex_machina.clustereducation;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class PollConstructor extends Fragment {


    public PollConstructor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_poll_constructor, container, false);

        FloatingActionButton buttonAdd = rootView.findViewById(R.id.floating_button_to_add_question);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout container = rootView.findViewById(R.id.container_in_constructor);

                LayoutInflater inflater = LayoutInflater.from(rootView.getContext());
                View inflatedLayout= inflater.inflate(R.layout.question_card, null, false);
                container.addView(inflatedLayout);
            }
        });
        return rootView;
    }

}
