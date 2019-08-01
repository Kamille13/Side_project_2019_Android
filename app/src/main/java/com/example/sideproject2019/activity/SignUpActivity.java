package com.example.sideproject2019.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sideproject2019.R;
import com.example.sideproject2019.SingletonVolley;
import com.example.sideproject2019.UserSingleton;
import com.example.sideproject2019.model.User;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText etFirstName = findViewById(R.id.etFirstName);
        final EditText etLastName = findViewById(R.id.etLastName);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        Button btInscription = findViewById(R.id.btInscription);

        btInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = etFirstName.getText().toString();
                String lastname = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()) {
                    new AlertDialog.Builder(SignUpActivity.this)
                            .setTitle("Erreur")
                            .setMessage("Veuillez renseigner vos informations")
                            .show();
                }
                User user = new User();
                user.setFirstName(firstname);
                user.setLastName(lastname);
                user.setEmail(email);
                user.setPassword(password);

                SingletonVolley.getInstance(SignUpActivity.this).SignUp(user, new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        UserSingleton.getInstance().setUser(user);
                        Intent intent = new Intent(SignUpActivity.this, MapsActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
