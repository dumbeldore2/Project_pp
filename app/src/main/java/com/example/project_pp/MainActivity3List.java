package com.example.project_pp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity3List extends ArrayAdapter<String> {

    Context context;
    String string[];

    public MainActivity3List(@NonNull Context c, String s[]) {
        super(c, R.layout.main3_list,R.id.list_view_1,s);
        this.context = c;
        this.string = s;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.main3_list,parent,false);

        TextView textView1 = row.findViewById(R.id.list_view_1_text1);
        TextView textView2 = row.findViewById(R.id.list_view_1_text2);

        int i = position + 1;

        textView1.setText(i+"");
        setColorToGradiant(textView1);
        textView2.setText(string[position]);

        return row;
    }

    //de functie van de verf lol
    private void setColorToGradiant(TextView textView){
        TextPaint textPaint = textView.getPaint();
        float width = textPaint.measureText(textView.getText().toString());

        Shader shader = new LinearGradient(width/2,0,width/2,textView.getTextSize(),
                new int[]{
                        Color.parseColor("#00BF8F"),
                        Color.parseColor("#001510")
                },null,Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
    }
}
