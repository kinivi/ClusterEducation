package com.projects.deus_ex_machina.clustereducation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


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
        final View rootView;

        rootView = inflater.inflate(R.layout.fragment_subject, container, false);

        //Array of objects containing icons and subject names
        final ArrayList<SubjectCard> subjects = new ArrayList<>();

        subjects.add(new SubjectCard("English", R.drawable.ic_subject_english));
        subjects.add(new SubjectCard("History of Ukraine", R.drawable.ic_subject_history));
        subjects.add(new SubjectCard("Intro to Specialty", R.drawable.ic_subject_intro_to_specialty));
        subjects.add(new SubjectCard("Linear Algebra", R.drawable.ic_subject_linear_algebra));
        subjects.add(new SubjectCard("Math Analysis", R.drawable.ic_subject_math_analysis));
        subjects.add(new SubjectCard("Physics", R.drawable.ic_subject_physics));
        subjects.add(new SubjectCard("Programming", R.drawable.ic_subject_programming));
        subjects.add(new SubjectCard("Teamwork and presentation skills", R.drawable.ic_subject_teamwork));

        final SubjectCardAdapter subjectCardAdapter = new SubjectCardAdapter(getActivity(), subjects);

        GridView subjectsView = rootView.findViewById(R.id.subject_grid_view);

        subjectsView.setAdapter(subjectCardAdapter);

        subjectsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater inflater1 = getActivity().getLayoutInflater();
                SubjectCard[] objects = new SubjectCard[8];
                objects = subjects.toArray(objects);
                String sub_name = objects[i].getSubjectName();
                Intent subjectClick = new Intent(getContext(), BlankContainerActivity.class);
                subjectClick.putExtra("TypeOfFragment", "OpenSubjectFragment");
                subjectClick.putExtra("Name", sub_name);
                startActivity(subjectClick);
            }
        });

        //return inflater.inflate(R.layout.fragment_subject, container, false);
        return subjectsView;
    }

}
