package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    EditText profile_username;
    EditText profile_password;
    EditText profile_surname;
    EditText profile_first_name;
    EditText profile_email_address;
    EditText profile_phone_number;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //Get controls from screen
        submitBtn = (Button)  findViewById(R.id.btn_submit);
        profile_username = (EditText) findViewById(R.id.profile_username);
        profile_password = (EditText) findViewById(R.id.profile_password);
        profile_surname = (EditText) findViewById(R.id.profile_surname);
        profile_first_name = (EditText) findViewById(R.id.profile_first_name);
        profile_email_address = (EditText) findViewById(R.id.profile_email_address);
        profile_phone_number = (EditText) findViewById(R.id.profile_phone_number);

        submitBtn.setOnClickListener(this);

        String username = profile_username.getText().toString();
        String password = profile_password.getText().toString();
        String surname = profile_password.getText().toString();
        String firstname = profile_first_name.getText().toString();
        String EmailAddress = profile_email_address.getText().toString();
        String PhoneNumber = profile_phone_number.getText().toString();

    }



    public void onClick(View v) {



        if(v == submitBtn){
            String username = profile_username.getText().toString();
            String password = profile_password.getText().toString();
            String surname = profile_username.getText().toString();
            String firstname = profile_username.getText().toString();
            String EmailAddress = profile_email_address.getText().toString();
            String PhoneNumber = profile_username.getText().toString();


            Intent myIntent = new Intent(Profile.this, Record_data.class);
            myIntent.putExtra("username", username);
            myIntent.putExtra("password", password);
            myIntent.putExtra("surname", surname);
            myIntent.putExtra("first name", firstname);
            myIntent.putExtra("phone number", PhoneNumber);


            Profile.this.startActivity(myIntent);
        }

    }
}