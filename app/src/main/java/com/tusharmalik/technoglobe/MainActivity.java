package com.tusharmalik.technoglobe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnCreate,btnShowPassword,btnHidePassword;
    EditText edMobNum,edPassword;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String mobNumberLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate = findViewById(R.id.btnCreate);
        btnLogin = findViewById(R.id.btnLogin);
        edMobNum =  findViewById(R.id.edMobNum);
        edPassword = findViewById(R.id.edPassword);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging In");
        progressDialog.setMessage("Please wait while we check your credentials.");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!edMobNum.getText().toString().isEmpty() && !edPassword.getText().toString().isEmpty()){
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(edMobNum.getText().toString().concat("@technoglobe.com"),edPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(MainActivity.this, "Enter Email and Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });


    }
}
