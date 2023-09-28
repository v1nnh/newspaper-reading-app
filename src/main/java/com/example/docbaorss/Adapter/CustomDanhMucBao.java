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

import com.example.docbaorss.Class.Bao;
import com.example.docbaorss.R;



import java.util.List;


public class CustomDanhMucBao extends ArrayAdapter<Bao> {

    Context context;
    int resource;
    List<Bao> objects;

    public static int pos;

    public CustomDanhMucBao(@NonNull Context context, int resource, @NonNull List<Bao> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder viewholder;
        if (convertView == null){
            viewholder = new Viewholder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_dsbao,parent,false);
            viewholder.mIcon = convertView.findViewById(R.id.imageview_icon);
            viewholder.tenDanhMuc = convertView.findViewById(R.id.textview_tendanhmuc);
            convertView.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertView.getTag();
        }

        Bao danhMucBao = objects.get(position);
        viewholder.mIcon.setImageResource(danhMucBao.getIcon());
        viewholder.tenDanhMuc.setText(danhMucBao.getTenBao());
        pos = position;
        return convertView;
    }

    public class Viewholder{
        ImageView mIcon;
        TextView tenDanhMuc;
    }

}
