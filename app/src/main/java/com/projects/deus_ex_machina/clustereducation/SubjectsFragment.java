package com.projects.deus_ex_machina.clustereducation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectsFragment extends Fragment {

    public SubjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_subject, container, false);

        //Array of objects containing icons and subject names
        ArrayList<SubjectCard> subjects = new ArrayList<>();

        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.internet_of_things));

        SubjectCardAdapter subjectCardAdapter = new SubjectCardAdapter(getActivity(), subjects);

        GridView subjectsView = rootView.findViewById(R.id.subject_grid_view);

        subjectsView.setAdapter(subjectCardAdapter);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_subject, container, false);
        return subjectsView;
    }

}
