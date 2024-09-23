package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button, Register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);




    button = findViewById(R.id.Log);
    Register = findViewById(R.id.Reg);


    button.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, LoginP.class);
                startActivity(intent);


        }

    });
    Register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, RegisterP.class);
            startActivity(intent);

        }
    });


    }
}