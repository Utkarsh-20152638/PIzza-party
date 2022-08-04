package com.example.android.pizza_party;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class orderHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third__page);

        Dbhelper2 helper = new Dbhelper2(this);
        ArrayList<Pizza> list = helper.getOrdershistory();

                orderhistory_Adapter adapter = new orderhistory_Adapter(list, this);
                RecyclerView recyclerView = findViewById(R.id.d_recyclerview);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                Collections.reverse(list);


    }
}