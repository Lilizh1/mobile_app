package com.he.layouttest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText EditText1;
    private EditText EditText2;
    private EditText EditText3;
    private Button button;
    private LinearLayout myLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText1 = (EditText) findViewById(R.id.editText1);
        EditText2 = (EditText) findViewById(R.id.editText2);
        EditText3 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        myLayout = (LinearLayout) findViewById(R.id.linearlayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText3.setText("");
                button.setBackgroundColor(Color.RED);
            }
        });

    }
}
