package com.example.android.pizza_party;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orderhistory_Adapter extends RecyclerView.Adapter<viewHolder> {

    ArrayList<Pizza> arrayList;
    Context context;

    public orderhistory_Adapter(ArrayList<Pizza> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_listview,parent,false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Pizza pizza = arrayList.get(position);

        int id = pizza.getId();
        holder.imageView.setImageResource(pizza.getImgid());

        holder.textView1.setText(pizza.getName());
        holder.textView2.setText(pizza.getQuantity() + "");
        //   int prices = pizza.getQuantity() * pizza.getPrice();
        holder.textView4.setText(pizza.getPrice() + "");
        Dbhelper2 helper = new Dbhelper2(context);

        Cursor cursor = helper.getOrderbyId(id);
        int tprice = cursor.getInt(2) * pizza.getQuantity();
        holder.textView4.setText(tprice + "");

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cursor.getString(1);

                int imageid = cursor.getInt(3);
                int qua = Integer.parseInt(holder.textView2.getText().toString());
                int price = cursor.getInt(2);
                int tprice = cursor.getInt(2) * qua;
                boolean isinserted = helper.updateOrder(name, price, qua, id, imageid);

                holder.textView4.setText(tprice + "");
                if (true)
                    Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Dbhelper2 helper = new Dbhelper2(context);
                helper.deletes(pizza.getId());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
class viewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView1;
    EditText textView2;
    Button button1;
    TextView textView4;
    Button checkbox1;
    Button button2;
    Button payment;
    public viewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.d_iv1);
        textView1 = itemView.findViewById(R.id.d_tv1);
        textView2 = itemView.findViewById(R.id.d_tv2);

        button1 = itemView.findViewById(R.id.d_edit);
        button1.setVisibility(View.GONE);
        payment =itemView.findViewById(R.id.payment);
        checkbox1 = itemView.findViewById(R.id.d_cb1);
        checkbox1.setVisibility(View.GONE);
        textView4 = itemView.findViewById(R.id.d_tv4);

    }


}
