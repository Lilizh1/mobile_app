package com.he.week10_advanceui2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsDetailFragment extends Fragment {
    private TextView newsDetailTxt;
    private TextView newsTitleTxt;

    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_news_detail, container, false);
        newsTitleTxt = (TextView) ret.findViewById(R.id.newsTitleTxt);
        newsDetailTxt = (TextView) ret.findViewById(R.id.newsDetailTxt);

        Bundle arguments = getArguments();
        if (arguments != null) {
            long id = arguments.getLong("id");
            newsTitleTxt.setText("新闻标题 " + ((id < 10) ? "0" + id : id));
            newsDetailTxt.setText("此处显示新闻内容" + ((id < 10) ? "0" + id : id)
                    + "\n  为简单，”新闻“内容是固定的。可改为基于文件系统或数据库的真新闻。");
        }
        return ret;
    }

    public void setNews(int id) {
        newsTitleTxt.setText("新闻标题 " + ((id < 10) ? "0" + id : id));
        newsDetailTxt.setText("此处显示新闻内容" + ((id < 10) ? "0" + id : id)
                + "\n  为简单起见，”新闻“内容是固定的。请考虑改为基于文件系统或数据库的真新闻。"
                + "\n 这是在平板中的显示，是否值得庆祝一下？");
    }

}
