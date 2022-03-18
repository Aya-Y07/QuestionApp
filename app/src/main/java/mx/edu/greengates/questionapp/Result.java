package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Result extends AppCompatActivity {
    private TextView accuracy_rate;
    private TextView TotalScore;
    private Button go_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        go_Home = (Button) findViewById(R.id.btn_go_home);
        go_Home.setOnClickListener((View.OnClickListener)this);

        accuracy_rate = (TextView) findViewById(R.id.accuracy_rate);
        TotalScore = (TextView) findViewById(R.id.total_score);


        int score = getIntent().getIntExtra("score", 0);

        int Accuracy_rate = 0;
        Accuracy_rate = (score/4)*100;

        accuracy_rate.setText(Accuracy_rate);
        TotalScore.setText(score);
    }

    public void onClick(View v) {
        if(v == go_Home){
            Intent myIntent = new Intent(Result.this, Home.class);
        }

    }
}