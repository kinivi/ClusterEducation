package com.projects.deus_ex_machina.clustereducation;

import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.androidadvance.androidsurvey.models.SurveyProperties;

import java.util.ArrayList;

/**
 * Created by Nikita Kiselov on 12-Dec-17.
 * ClusterEducation
 */

public class SurveyConstructor {

    public SurveyConstructor() {

    }

    public SurveyPojo get(){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("DF");

        Question question = new Question();
        ArrayList<String> choices = new ArrayList<String>();
        ArrayList<Question> questions = new ArrayList<Question>();

        SurveyPojo pojo = new SurveyPojo();
        SurveyProperties properties = new SurveyProperties();

        properties.setTitle("Title");
        properties.setEndMessage("End message");
        properties.setIntroMessage("Intro");
        properties.setSkipIntro(false);



        choices.add("1");
        choices.add("2");
        question.setDescription("");
        question.setChoices(choices);
        question.setRandomChoices(false);
        question.setRequired(true);
        question.setQuestionType("Checkboxes");
        question.setQuestionTitle("Title");

        questions.add(question);

        pojo.setSurveyProperties(properties);
        pojo.setQuestions(questions);

        return pojo;
    }

}

class Survey {
    public ArrayList<Question> questions;

    public Survey(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Survey() {
    }
}


