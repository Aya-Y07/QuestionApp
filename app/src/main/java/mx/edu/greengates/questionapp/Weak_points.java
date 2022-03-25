package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Weak_points extends AppCompatActivity {

    private Button math_weak_points;
    private Button physics_weak_points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weak_points);

        math_weak_points = (Button) findViewById(R.id.btn_weak_point_math);
        math_weak_points.setOnClickListener((View.OnClickListener)this);

        physics_weak_points = (Button) findViewById(R.id.btn_weak_point_physics);
        physics_weak_points.setOnClickListener((View.OnClickListener)this);
    }

    public void onClick(View v) {

        if (v == math_weak_points) {
            Intent myIntent = new Intent(Weak_points.this, Texts_Math.class);
        }
        if (v == physics_weak_points) {
            Intent myIntent = new Intent(Weak_points.this, Texts_Physics.class);
        }
    }


}