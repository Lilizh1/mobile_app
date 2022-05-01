package com.he.week9_advanceui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2022/4/25.
 */

public class TextImageAdapter extends BaseAdapter {
    private Context mContext;
    //展示的文字
    private List<String> texts;
    //展示的图片
    private List<Integer> images;

    public TextImageAdapter(Context mContext, List<String> texts, List<Integer> images) {
        this.mContext = mContext;
        this.texts = texts;
        this.images = images;
    }

    //元素的个数 @Override
    public int getCount() {
        return texts.size();
    }

    //取得选中的项目的值，本例中不支持 @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //用于生成在 Listview 中展示的一个 View 元素
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item, null);
            ItemViewCache viewCache = new ItemViewCache();
            viewCache.mTextView = (TextView) convertView
                    .findViewById(R.id.itemListView4Txt);
            viewCache.mImageView = (ImageView) convertView
                    .findViewById(R.id.itemListView4Img);
            convertView.setTag(viewCache);
        }
        ItemViewCache cache = (ItemViewCache) convertView.getTag();
        cache.mTextView.setText(texts.get(position));
        cache.mImageView.setImageResource(images.get(position));
        return convertView;
    }

    //创建内部私有类
    private class ItemViewCache {
        public TextView mTextView;
        public ImageView mImageView;
    }
}

