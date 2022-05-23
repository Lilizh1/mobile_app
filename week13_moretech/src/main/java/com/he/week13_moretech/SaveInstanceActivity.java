package com.he.week13_moretech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SaveInstanceActivity extends AppCompatActivity {
    private final String GAME_STATE_KEY = "gameState";
    private final String GAME_CREDITS_KEY = "gameCredits";
    private  Myapplication app;
    private TextView global_veriable_txt;
    private EditText game_credits_edt;
    String gameState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }
        setContentView(R.layout.activity_save_instance);
        app = (Myapplication)getApplication();
        global_veriable_txt = (TextView)findViewById(R.id.global_variable_txt);
        global_veriable_txt.setText("用户姓名"+app.getUserName()+"\n机构名称："+app.getOrgName());
        game_credits_edt = (EditText) findViewById(R.id.game_credits_edt);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(GAME_STATE_KEY,gameState);
        outState.putString(GAME_CREDITS_KEY,game_credits_edt.getText().toString());
        super.onSaveInstanceState(outState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState){
        game_credits_edt.setText(savedInstanceState.getString(GAME_CREDITS_KEY));
    }
}
