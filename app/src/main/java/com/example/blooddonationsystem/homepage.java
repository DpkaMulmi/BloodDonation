package com.example.blooddonationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        Button button = (Button) findViewById(R.id.log_out);
        TextView userMsg = (TextView) findViewById(R.id.textView2);
        userMsg.setText("Welcome " +name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Login.class);
                startActivity(intent);
    }
            });
    }
}