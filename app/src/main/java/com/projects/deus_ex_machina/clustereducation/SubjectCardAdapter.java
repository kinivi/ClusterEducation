package com.projects.deus_ex_machina.clustereducation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael on 12/10/2017
 */

public class SubjectCardAdapter extends ArrayAdapter<SubjectCard>{

    public SubjectCardAdapter(Activity context, ArrayList<SubjectCard> subjects) {
        super(context, 0, subjects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        if(gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.card_subject, parent, false);
        }
        //Get the position of the item to be displayed
        final SubjectCard currentSubjectCard = getItem(position);
        //Set text
        TextView subjectName = gridItemView.findViewById(R.id.subject_name_text_view);
        subjectName.setText(currentSubjectCard.getSubjectName());

        //Set image
        ImageView subjectImage = gridItemView.findViewById(R.id.subject_image_view);
        subjectImage.setImageResource(currentSubjectCard.getImageResourceId());

        return gridItemView;
    }
}


