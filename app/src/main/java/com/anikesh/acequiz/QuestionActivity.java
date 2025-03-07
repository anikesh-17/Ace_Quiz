package com.anikesh.acequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity extends AppCompatActivity {

    String questions[] = {

            "Which of the following comment is correct when a macro definition includes arguments?",
            "What is a lint?",
            "Why is a macro used in place of a function?",
            "In the C language, the constant is defined _______.",
            " Which one of the following is a loop construct that will always be executed once?"

    };
    String answer[] = {
            "The opening parenthesis should immediately follow the macro name.",
            "Analyzing tool",
            "It reduces code size.",
            "Anywhere, but starting on a new line.",
            "do while",
    };
    String option[] = {
            "The opening parenthesis should immediately follow the macro name.","There should be at least one blank between the macro name and the opening parenthesis.","There should be only one blank between the macro name and the opening parenthesis.","All the above comments are correct.",

            "C compiler","Interactive debugger","Analyzing tool","C interpreter",

            "It reduces execution time.","It reduces code size.","It increases execution time.","It increases code size.",

            "Before main","After main","Anywhere, but starting on a new line.","None of the these.",

            "for","while","switch","do while"
    };

    int flag=0;

    public static int marks = 0, correct=0, wrong=0;
    TextView tv;
    Button submitbutton,quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    private TextView questionnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView score = (TextView) findViewById(R.id.textview4);
        TextView textview = (TextView) findViewById(R.id.DisplayName);
        Intent intent = getIntent();

        questionnumber = findViewById(R.id.DisplayName);
        submitbutton = (Button) findViewById(R.id.button3);
        quitbutton = (Button) findViewById(R.id.buttonquit);
        tv = (TextView) findViewById(R.id.tvquestion);

        radio_g = (RadioGroup) findViewById(R.id.answergrp);
        rb1 = (RadioButton) findViewById(R.id.radiobutton);
        rb2 = (RadioButton) findViewById(R.id.radiobutton2);
        rb3 = (RadioButton) findViewById(R.id.radiobutton3);
        rb4 = (RadioButton) findViewById(R.id.radiobutton4);

        tv.setText(questions[flag]);
        rb1.setText(option[0]);
        rb2.setText(option[1]);
        rb3.setText(option[2]);
        rb4.setText(option[3]);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio_g.getCheckedRadioButtonId() == -1){
                    Toast.makeText(QuestionActivity.this, "Please Select one Choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();

                if(ansText.equals(answer[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                }
                else{
                    wrong++;
                    Toast.makeText(QuestionActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                flag++;

                if(score!=null){
                    score.setText(""+correct);

                    if(flag<questions.length){
                        tv.setText(questions[flag]);
                        rb1.setText(option[flag*4]);
                        rb2.setText(option[flag*4+1]);
                        rb3.setText(option[flag*4+2]);
                        rb4.setText(option[flag*4+3]);
                        questionnumber.setText(flag+"/"+questions.length+"Question");
                    }
                    else{
                        marks = correct;
                        Intent in = new Intent(QuestionActivity.this,ResultActivity.class);
                        startActivity(in);
                    }
                    radio_g.clearCheck();

                }
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent1);
            }
        });

    }
}