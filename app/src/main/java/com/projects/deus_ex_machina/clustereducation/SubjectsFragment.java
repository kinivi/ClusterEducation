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

        subjects.add(new SubjectCard("English", R.drawable.ic_subject_english));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.ic_subject_history));
        subjects.add(new SubjectCard("Intro to Specialty", R.drawable.ic_subject_intro_to_specialty));
        subjects.add(new SubjectCard("Linear Algebra", R.drawable.ic_subject_linear_algebra));
        subjects.add(new SubjectCard("Math Analysis", R.drawable.ic_subject_math_analysis));
        subjects.add(new SubjectCard("Physics", R.drawable.ic_subject_physics));
        subjects.add(new SubjectCard("Programming", R.drawable.ic_subject_programming));
        subjects.add(new SubjectCard("Teamwork and presentation skills", R.drawable.ic_subject_teamwork));

        SubjectCardAdapter subjectCardAdapter = new SubjectCardAdapter(getActivity(), subjects);

        GridView subjectsView = rootView.findViewById(R.id.subject_grid_view);

        subjectsView.setAdapter(subjectCardAdapter);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_subject, container, false);
        return subjectsView;
    }

}
