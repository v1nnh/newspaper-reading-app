package com.example.docbaorss.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.example.docbaorss.Class.ItemsRss;
import com.example.docbaorss.R;

import java.util.List;

public class CustomDanhSachBaiBao extends ArrayAdapter<ItemsRss> {


    @NonNull
    Context context;
    int resource;
    @NonNull
    List<ItemsRss> objects;

    public CustomDanhSachBaiBao(@NonNull Context context, int resource, @NonNull List<ItemsRss> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_dsbaibao, parent, false);
            viewHolder.anhbaibao = convertView.findViewById(R.id.img_anhbaibao);
            viewHolder.tieudebaibao = convertView.findViewById(R.id.tv_tieudebaibao);



            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemsRss itemsRss = objects.get(position);
        viewHolder.anhbaibao.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(itemsRss.getUrlImg()).into(viewHolder.anhbaibao);
        viewHolder.tieudebaibao.setText(itemsRss.getTitle());

        return convertView;
    }

    public class ViewHolder {
        ImageView anhbaibao;
        TextView tieudebaibao;


    }

}
