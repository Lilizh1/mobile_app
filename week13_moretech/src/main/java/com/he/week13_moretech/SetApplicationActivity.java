package com.he.week13_moretech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetApplicationActivity extends AppCompatActivity {
    private  Myapplication app;
    private EditText userNameEdt,orgNameEdt;
    private Button updateBnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_application);
        app = (Myapplication)getApplication();

        userNameEdt = (EditText)findViewById(R.id.userNameEdt);
        orgNameEdt = (EditText)findViewById(R.id.orgNameEdt);
        updateBnt = (Button) findViewById(R.id.updateBnt);

        userNameEdt.setText(app.getUserName());
        orgNameEdt.setText(app.getOrgName());

        updateBnt.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                app.setUserName(userNameEdt.getText().toString());
                app.setOrgName(orgNameEdt.getText().toString());
                finish();
            }
        });
    }
}
