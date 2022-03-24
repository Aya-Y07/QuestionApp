package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

import mx.edu.greengates.questionapp.data.model.User_folder;
import mx.edu.greengates.questionapp.data.model.Users;


public class Profile extends AppCompatActivity implements View.OnClickListener {
        private EditText profile_username;
        private EditText profile_password;
        private EditText profile_surname;
        private EditText profile_first_name;
        private EditText profile_email_address;
        private EditText profile_phone_number;
        private Button submitBtn;
        private Users user_info;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user_info = User_folder.getUsersFromCSV(this);


            submitBtn = (Button)  findViewById(R.id.btn_profile_submit);
            profile_username = (EditText) findViewById(R.id.profile_username);
            profile_password = (EditText) findViewById(R.id.profile_password);
            profile_surname = (EditText) findViewById(R.id.profile_surname);
            profile_first_name = (EditText) findViewById(R.id.profile_firstname);
            profile_email_address = (EditText) findViewById(R.id.profile_email_address);
            profile_phone_number = (EditText) findViewById(R.id.prodfile_phone_number);

            submitBtn.setOnClickListener(this);

            submitBtn.setOnClickListener(view -> {
                String username = profile_username.getText().toString();
                String firstname = profile_first_name.getText().toString();
                String surname = profile_surname.getText().toString();
                String password = profile_password.getText().toString();
                String email = profile_email_address.getText().toString();
                String phoneNumber = profile_phone_number.getText().toString();

            });

    }


}