package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginP extends AppCompatActivity {
private EditText e1,e2;
private String User,Password;
private Button Signin;
private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_p);

        e1=findViewById(R.id.editTextText5);
        e2 = findViewById(R.id.editTextText8);
        Signin = findViewById(R.id.button);
        dbHelper = new DBHelper(this);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User=e1.getText().toString();
                Password=e2.getText().toString();
                boolean isLoggedId = dbHelper.checkUser(User,Password);
                if (isLoggedId) {
                    Intent intent = new Intent( LoginP.this, HomeP.class);
                    Toast.makeText(  LoginP.this, "Login Successfully", Toast. LENGTH_LONG) . show() ;
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(  LoginP.this, "Login Failed", Toast. LENGTH_LONG) . show() ;


                }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}