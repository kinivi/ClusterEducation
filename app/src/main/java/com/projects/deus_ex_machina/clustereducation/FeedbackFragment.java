package com.projects.deus_ex_machina.clustereducation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement
 */
public class FeedbackFragment extends Fragment {

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);

        EditText editTextName = rootView.findViewById(R.id.name_field).findViewById(R.id.edit_text);
        editTextName.setHint("Please, write your name");

        EditText editTextFeedback = rootView.findViewById(R.id.feedback_field).findViewById(R.id.edit_text);
        editTextFeedback.setHint("Write your feedback");
        return rootView;
    }
}
