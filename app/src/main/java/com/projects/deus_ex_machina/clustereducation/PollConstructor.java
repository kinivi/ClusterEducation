package com.projects.deus_ex_machina.clustereducation;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.androidadvance.androidsurvey.models.SurveyProperties;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PollConstructor extends Fragment {

    private int counterOfQuestions = 0;
    private SurveyPojo survey;
    private SurveyProperties surveyProperties;
    private ArrayList<Question> questionArrayList;


    public PollConstructor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_poll_constructor, container, false);

        final LinearLayout linearLayout = rootView.findViewById(R.id.container_in_constructor);

        //Initialize variables
        survey = new SurveyPojo();
        surveyProperties = new SurveyProperties();
        questionArrayList = new ArrayList<Question>();

        FloatingActionButton buttonAdd = rootView.findViewById(R.id.floating_button_to_add_question);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = LayoutInflater.from(rootView.getContext());
                final View inflatedLayout = inflater.inflate(R.layout.question_card, null, false);
                linearLayout.addView(inflatedLayout);

                ((TextView) inflatedLayout.findViewById(R.id.question_card_title)).setText("Question card");


                Button buttonAddAnswer = inflatedLayout.findViewById(R.id.button_add_answer);
                buttonAddAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinearLayout container = inflatedLayout.findViewById(R.id.container_for_answers);

                        EditText answerEditText = new EditText(rootView.getContext());
                        answerEditText.setHint("New answer");

                        container.addView(answerEditText);

                    }
                });
            }
        });


        FloatingActionButton buttonSaveSurvey = rootView.findViewById(R.id.floating_button_to_save_survey);
        buttonSaveSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                survey = new SurveyPojo();
                surveyProperties = new SurveyProperties();
                questionArrayList = new ArrayList<Question>();

                EditText titleEditText = rootView.findViewById(R.id.SurveyTitle);
                EditText introMessage = rootView.findViewById(R.id.SurveyIntroMessage);
                EditText endMessage = rootView.findViewById(R.id.SurveyEndMessage);


                if (titleEditText.getText().toString().equals("")) {
                    surveyProperties.setTitle("");
                } else {
                    surveyProperties.setTitle(titleEditText.getText().toString());
                }

                if (introMessage.getText().toString().equals("")) {
                    surveyProperties.setIntroMessage("");
                } else {
                    surveyProperties.setIntroMessage(introMessage.getText().toString());
                }

                if (endMessage.getText().toString().equals("")) {
                    surveyProperties.setEndMessage("");
                } else {
                    surveyProperties.setEndMessage(endMessage.getText().toString());
                }



                for (int i = 3; i < linearLayout.getChildCount(); i++) {
                    ArrayList<String> choises = new ArrayList<String>();
                    Question question = new Question();
                    String description = "";


                    View counterView = linearLayout.getChildAt(i);
                    EditText editTextTitle = rootView.findViewById(R.id.title_edit_text);

                    LinearLayout container = counterView.findViewById(R.id.container_for_answers);

                    for (int j = 0; j < container.getChildCount(); j++) {
                        EditText editText = (EditText) container.getChildAt(j);
                        choises.add(editText.getText().toString());
                    }

                    RadioGroup radioGroup = counterView.findViewById(R.id.type_of_answers);
                    RadioButton checkedButton = counterView.findViewById(radioGroup.getCheckedRadioButtonId());


                    question.setChoices(choises);
                    question.setRandomChoices(false);
                    question.setRequired(true);

                    if (editTextTitle.getText().toString().equals("")) {
                        question.setQuestionTitle("Question #" + i);
                    } else {
                        question.setQuestionTitle(editTextTitle.getText().toString());
                    }

                    if (editTextTitle.getText().toString().equals("")) {
                        question.setDescription("");
                    } else {
                        question.setDescription(((EditText) rootView.findViewById(R.id.description_edit_text)).getText().toString());
                    }

                    question.setQuestionType(checkedButton.getText().toString());
                    questionArrayList.add(question);

                }

                surveyProperties.setSkipIntro(false);

                survey.setQuestions(questionArrayList);
                survey.setSurveyProperties(surveyProperties);

                String key = FirebaseDatabase.getInstance().getReference().child("polls").push().getKey();
                FirebaseDatabase.getInstance().getReference().child("polls/" + key).setValue(survey);

                for (int i = 0; i < questionArrayList.size(); i++) {
                    for (int j = 0; j < questionArrayList.get(i).getChoices().size(); j++) {
                        FirebaseDatabase.getInstance().getReference().child("polls_answer/" + key + "/" + i + "/" + questionArrayList.get(i).getChoices().get(j)).setValue(0);
                    }
                }


            }
        });


        return rootView;
    }


}
