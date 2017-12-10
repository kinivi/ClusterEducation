package com.projects.deus_ex_machina.clustereducation;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Michael on 12/7/2017
 */

public class SubjectCard {
    private String mSubjectName;
    private int mImageResourceId;

    public SubjectCard(String SubjectName, int ImageResourceId) {
        mSubjectName = SubjectName;
        mImageResourceId = ImageResourceId;
    }

    public String getSubjectName() {
        return mSubjectName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}
