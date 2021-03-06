package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class Texts_Physics extends AppCompatActivity implements View.OnClickListener {

    private Button topic_1;
    private Button topic_2;
    private Button topic_3;
    private Button topic_4;

    private Button ans_1;
    private Button ans_2;
    private Button ans_3;
    private Button ans_4;

    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texts_physics);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        topic_1 = (Button) findViewById(R.id.questions_1);
        topic_1.setOnClickListener((View.OnClickListener) this);
        topic_2 = findViewById(R.id.questions_2);
        topic_2.setOnClickListener((View.OnClickListener) this);
        topic_3 = findViewById(R.id.questions_3);
        topic_3.setOnClickListener((View.OnClickListener) this);
        topic_4 = findViewById(R.id.questions_4);
        topic_4.setOnClickListener((View.OnClickListener) this);

        ans_1 = (Button) findViewById(R.id.solutions_1);
        ans_1.setOnClickListener((View.OnClickListener) this);
        ans_2 = (Button) findViewById(R.id.solutions_2);
        ans_2.setOnClickListener((View.OnClickListener) this);
        ans_3 = (Button) findViewById(R.id.solutions_3);
        ans_3.setOnClickListener((View.OnClickListener) this);
        ans_4 = (Button) findViewById(R.id.solutions_4);
        ans_4.setOnClickListener((View.OnClickListener) this);

    }

    public void onClick (View v){
        String quiz = "";
        if(v == topic_1){
            Intent myIntent = new Intent(Texts_Physics.this, Questions_multiple_choice.class);
            quiz = "Topic_1_Images";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == topic_2){
            Intent myIntent = new Intent(Texts_Physics.this, Questions_multiple_choice.class);
            quiz = "Topic_2_Images";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == topic_3){
            Intent myIntent = new Intent(Texts_Physics.this, Questions_multiple_choice.class);
            quiz = "Topic_3_Images";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == topic_4){
            Intent myIntent = new Intent(Texts_Physics.this, Questions_multiple_choice.class);
            quiz = "Topic_4_Images";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }

        if(v == ans_1){
            Intent myIntent = new Intent(Texts_Physics.this, Solutions.class);
            quiz = "solution_topic_1";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_2){
            Intent myIntent = new Intent(Texts_Physics.this, Solutions.class);
            quiz = "solution_topic_2";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_3){
            Intent myIntent = new Intent(Texts_Physics.this, Solutions.class);
            quiz = "solution_topic_3";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_4){
            Intent myIntent = new Intent(Texts_Physics.this, Solutions.class);
            quiz = "solution_topic_4";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
    }
}