package com.he.week10_advanceui2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class NewsListFragment extends Fragment {

    public interface OnNewsSelectedListener {
        void onNewsSelected(Bundle bundle);
    }

    //定义一个自定义接口的对象
    private OnNewsSelectedListener mOnNewsSelectedListener;

    public NewsListFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewsSelectedListener) {
            mOnNewsSelectedListener =
                    (OnNewsSelectedListener) context;
        } else {
            throw new IllegalArgumentException("Activity must OnNewsSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        mOnNewsSelectedListener = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_news_list, container, false);
        ListView listView = (ListView) ret.findViewById(R.id.news_list);
        listView.setOnItemClickListener(listener);
        if (listView != null) {
            ArrayList<String> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                data.add("新闻标题" + ((i < 10) ? "0" + i : i));
            }
            ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
                    getContext(), android.R.layout
                    .simple_list_item_1, data
            );
            listView.setAdapter(mAdapter);
        }
        return ret;
    }

    //设置处理列表项点击的事件监听器
    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mOnNewsSelectedListener !=
                    null) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putLong("id", id);
                mOnNewsSelectedListener.onNewsSelected(bundle);
            }
        }
    };
}
