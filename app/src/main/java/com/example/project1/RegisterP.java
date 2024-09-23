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

public class RegisterP extends AppCompatActivity {
    private EditText e3, e4, e5;
    private Button Signup;
    private String User, Password, Cpassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_p);
        e3 = findViewById(R.id.editTextText3);
        e4 = findViewById(R.id.editTextText10);
        Signup = findViewById(R.id.button);
        e5 = findViewById(R.id.editTextText);
        dbHelper = new DBHelper(this);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User = e3.getText().toString();
                Password = e4.getText().toString();
                Cpassword = e5.getText().toString();

                if (User.isEmpty() || Password.isEmpty() || Cpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "fill the details", Toast.LENGTH_SHORT).show();
                } else if (!Password.equals(Cpassword)) {
                    Toast.makeText(getApplicationContext(), "Enter the same Password", Toast.LENGTH_SHORT).show();
                } else {
                    if(Password.equals(Cpassword)) {
                        if (dbHelper.checkUsername(User)) {
                            Toast.makeText(RegisterP.this, "User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registeredSuccess = dbHelper.insertData(User, Password);
                        if (registeredSuccess)
                            Toast.makeText(RegisterP.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(RegisterP.this, "User Registration Failed", Toast.LENGTH_LONG).show();
                        }

                    Intent intent = new Intent(RegisterP.this, LoginP.class);
                   startActivity(intent);
                    }

                }
            }


        });

        ViewCompat.setOnApplyWindowInsetsListener(

    findViewById(R.id.main), (v,insets)->

    {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });

    }
}