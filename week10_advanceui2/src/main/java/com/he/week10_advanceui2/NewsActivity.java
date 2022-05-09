package com.he.week10_advanceui2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity implements NewsListFragment.OnNewsSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    @Override
    public void onNewsSelected(Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment detailFragment = manager.findFragmentById(R.id.fragment_detail);
        if(detailFragment != null ){
            NewsDetailFragment newsDetailFragment = (NewsDetailFragment) detailFragment;
            newsDetailFragment.setNews(bundle.getInt("position",0));
        }else{
            Intent intent = new Intent(this, NewsDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
