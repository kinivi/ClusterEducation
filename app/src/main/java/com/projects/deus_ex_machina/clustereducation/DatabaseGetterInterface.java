package com.projects.deus_ex_machina.clustereducation;

import java.util.ArrayList;

/**
 * Created by Nikita Kiselov on 27-Nov-17.
 * ClusterEducation
 */

public interface DatabaseGetterInterface {
    ArrayList<Integer> getValuesOfQuestion(int numberOfQuestion);
    ArrayList<Integer> getAnswersTextOfQuestion(int numberOfQuestion);
}
