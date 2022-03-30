package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button math;
    private Button physics;
    private Button graphs;
    private Button accuracy_rate;
    private Button profile;
    private Button weak_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        math = (Button) findViewById(R.id.btn_math);
        math.setOnClickListener((View.OnClickListener)this);

        physics = (Button) findViewById(R.id.btn_physics);
        physics.setOnClickListener((View.OnClickListener)this);

        graphs = (Button) findViewById(R.id.btn_graphs);
        graphs.setOnClickListener((View.OnClickListener)this);

        accuracy_rate = (Button) findViewById(R.id.btn_accuracy_rate);
        accuracy_rate.setOnClickListener((View.OnClickListener)this);

        profile = (Button) findViewById(R.id.btn_profile);
        profile.setOnClickListener((View.OnClickListener)this);

        weak_point = (Button) findViewById(R.id.btn_weak_point);
        weak_point.setOnClickListener((View.OnClickListener)this);

    }
    public void onClick(View v){

        if(v == math){
            Intent myIntent = new Intent(Home.this, Texts_Math.class);
        }
        if(v == physics){
            Intent myIntent = new Intent(Home.this, Texts_Physics.class);
        }
        if(v == graphs){
            Intent myIntent = new Intent(Home.this, graph_page.class);
        }
        if(v == accuracy_rate){
            Intent myIntent = new Intent(Home.this, Record_data.class);
        }if (v== profile);{
            Intent myIntent = new Intent(Home.this, Profile.class);
        }if (v== weak_point);{
            Intent myIntent = new Intent(Home.this, Review.class);
        }
    }
}