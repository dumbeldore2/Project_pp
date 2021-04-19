package com.example.project_pp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class MainActivity2List extends ArrayAdapter<String> {

    Context context;
    String string[];
    int anInt[];

    public MainActivity2List(@NonNull Context c, String s[], int i[]) {
        super(c, R.layout.main2_list , R.id.list_view_1 , s);
        this.context = c;
        this.string = s;
        this.anInt = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.main2_list,parent,false);
        ImageView images = row.findViewById(R.id.image_listview);
        TextView names = row.findViewById(R.id.text_listview);

        images.setImageResource(anInt[position]);
        names.setText(string[position]);

        return row;
    }
}