package com.he.week10_advanceui2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by lenovo on 2022/5/2.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[] imageIds;

    public ImageAdapter(Context context, int[] mThumbIds)
    { this.context = context;
        this.imageIds = mThumbIds;
    }

    @Override
    public int getCount()
    { return imageIds.length;
    }

    @Override
    public Object getItem(int position)
    { return imageIds[position];
    }

    @Override
    public long getItemId(int position)
    { return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(imageIds[position]);
        return imageView;
    }
}
