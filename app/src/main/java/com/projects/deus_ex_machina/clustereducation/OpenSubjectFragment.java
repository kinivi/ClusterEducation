package com.projects.deus_ex_machina.clustereducation;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Michael on 12/23/2017
 */

public class OpenSubjectFragment extends Fragment{
    private String name_of_a_subject;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_open_subject, null);
        TextView textView = view.findViewById(R.id.subject_namy);
        textView.setText(name_of_a_subject);
        Button button = view.findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }

    public void setName_of_a_subject(String name_of_a_subject) {
        this.name_of_a_subject = name_of_a_subject;
    }

    public String getName_of_a_subject() {
        return name_of_a_subject;
    }
}
