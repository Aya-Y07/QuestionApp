package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Texts_Math extends AppCompatActivity implements View.OnClickListener {

    private Button trig;
    private Button alg;
    private Button vec;
    private Button comp;

    private Button ans_trig;
    private Button ans_dif;
    private Button ans_vec;
    private Button ans_comp;

    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_math);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        trig = (Button) findViewById(R.id.question_trignometry);
        trig.setOnClickListener((View.OnClickListener)this);

        alg = (Button) findViewById(R.id.questions_algebra);
        alg.setOnClickListener((View.OnClickListener)this);

        vec = (Button) findViewById(R.id.questions_vector);
        vec.setOnClickListener((View.OnClickListener)this);

        comp = (Button) findViewById(R.id.questions_complex_numebr);
        comp.setOnClickListener((View.OnClickListener)this);

        ans_trig = (Button) findViewById(R.id.solutions_trignometry);
        ans_trig.setOnClickListener((View.OnClickListener)this);

        ans_dif = (Button) findViewById(R.id.solutions_algebra);
        ans_dif.setOnClickListener((View.OnClickListener)this);

        ans_vec = (Button) findViewById(R.id.solutions_vector);
        ans_vec.setOnClickListener((View.OnClickListener)this);

        ans_comp = (Button) findViewById(R.id.solutions_complex_numebr);
        ans_comp.setOnClickListener((View.OnClickListener)this);

    }
    public void onClick (View v){
        String quiz = "";

        if(v == trig){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "trig";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);

        }
        if(v == alg){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "alg";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == vec){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "vec";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == comp){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "comp";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }


        if(v == ans_trig){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "solution_trig";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_dif){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "dif";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_vec){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "vec";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_comp){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "comp";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }

    }
}