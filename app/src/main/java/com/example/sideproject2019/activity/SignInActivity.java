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

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText etEmail = findViewById(R.id.etEmailSignIn);
        final EditText etPassword = findViewById(R.id.etPassWordSignIn);
        Button btSignIn = findViewById(R.id.btSignIn);
        Button btSignUp = findViewById(R.id.btSignUpSignIn);

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    new AlertDialog.Builder(SignInActivity.this)
                            .setTitle("Erreur")
                            .setMessage("Veuillez renseigner votre email et mot de passe")
                            .show();
                }
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                SingletonVolley.getInstance(SignInActivity.this)
                        .login(user, new Consumer<User>() {
                            @Override
                            public void accept(User user) {
                                UserSingleton.getInstance().setUser(user);
                                if (user != null) {
                                    Intent intent = new Intent(SignInActivity.this,
                                            SignUpActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    new AlertDialog.Builder(SignInActivity.this)
                                            .setTitle("Erreur")
                                            .setMessage("Utilisateur Introuvable")
                                            .show();
                                }
                            }

                        });
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
