package com.projects.deus_ex_machina.clustereducation;

/**
 * Created by Nikita Kiselov on 25-Nov-17.
 * ClusterEducation
 */

class AnswerClass {

    public Integer Option1;
    public Integer Option2;
    public Integer Option3;
    public Integer Option4;

    public AnswerClass() {
        // Конструктор по умолчанию. Необходим для работы Firebase
    }

    public AnswerClass(Integer option1, Integer option2, Integer option3, Integer option4) {
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
    }
}
