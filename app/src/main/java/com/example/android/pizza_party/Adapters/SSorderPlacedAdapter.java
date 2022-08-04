package com.example.android.pizza_party.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.pizza_party.Pizza;
import com.example.android.pizza_party.R;

import java.util.ArrayList;

public class SSorderPlacedAdapter extends ArrayAdapter<Pizza> {

    public SSorderPlacedAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pizza> objects) {
        super(context, resource, objects);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.ss_placedorders, parent, false);
        }
    Pizza pizza = getItem(position);
        TextView textView2 = convertView.findViewById(R.id.POtextView2);
        TextView textView3 = convertView.findViewById(R.id.POtextView3);
        TextView textView4 = convertView.findViewById(R.id.POtextView4);
        TextView textView5 = convertView.findViewById(R.id.POtextView5);
        TextView textView6 = convertView.findViewById(R.id.POtextView6);
        TextView textView7 = convertView.findViewById(R.id.POtextView7);

        textView2.setText(""+pizza.getId());
        textView3.setText(""+pizza.getImgid());
        textView4.setText(""+pizza.getName());
        textView5.setText(""+pizza.getPrice());
        textView6.setText("Quantity: "+pizza.getQuantity());
        textView7.setText("Addr: "+pizza.getAddress ());


      return  convertView;
    }
}
