package com.example.android.pizza_party;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.pizza_party.Adapters.detailview_adapter;

import java.util.ArrayList;
import java.util.Collections;

public class DetailView extends AppCompatActivity {
    Button payment;
    ArrayList<Pizza> list;
    Dbhelper helper;
  //TextView totalMoney;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        helper  = new Dbhelper(this);
        list = helper.getOrders();

            int total = CalTotPrice(list);
            detailview_adapter adapter = new detailview_adapter(list, this);
            RecyclerView recyclerView = findViewById(R.id.d_recyclerview);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            Collections.reverse(list);

            payment = findViewById(R.id.payment);

            payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list = helper.getOrders();

                    if(!list.isEmpty()){

                        int total = CalTotPrice(list);
                       // totalMoney.setText(""+total);
                    Intent intent = new Intent(DetailView.this,Payment_Activity.class);
                    intent.putExtra("Price",total);

                    startActivity(intent);}
                    else{
                        Toast.makeText(DetailView.this, "Order is empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    public int CalTotPrice(ArrayList<Pizza> list) {
          int size = list.size();
          int totPrice = 0;
          for (int i = 0; i < size; i++) {
              Pizza pizza = list.get(i);
               int quan = pizza.getQuantity();
               int  price = pizza.getPrice();
               int tprice = quan * price;
              totPrice += tprice;

                                      }
          return totPrice;

    }


}