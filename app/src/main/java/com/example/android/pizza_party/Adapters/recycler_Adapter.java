package com.example.android.pizza_party.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.pizza_party.Dbhelper;
import com.example.android.pizza_party.Pizza;
import com.example.android.pizza_party.R;

import java.util.ArrayList;

public class recycler_Adapter extends RecyclerView.Adapter<recycler_Adapter.view_holder>  {
    ArrayList<Pizza> arrayList;
    Context context;int l=0;


    public recycler_Adapter(ArrayList<Pizza> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    int sum=0;
//  ////  Pizza pizza;
    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_items,parent,false);
        view_holder viewholder = new view_holder(view);

        return viewholder ;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        final Dbhelper helper =new Dbhelper(context);
    //    final Dbhelper2 helper2 =new Dbhelper2(context);
            Pizza pizza;
            pizza = arrayList.get(position);
           int imgid = pizza.getImgid();
        String name = pizza.getName();
        int price = pizza.getPrice();

        holder.imageView.setImageResource(pizza.getImgid());
            holder.textView1.setText(name);
            holder.textView4.setText(""+price);
        operation opr = new operation();

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = opr.getCount();
                if(i==0)
                    Toast.makeText(context,"Mini quantity",Toast.LENGTH_SHORT).show();
                else{
                    i--;
                    holder.textView2.setText(""+i);
                    opr.setCount(i);}
                pizza.setQuantity(i);
                if(i==0)holder.button3.setVisibility(View.GONE);
            }
        });
        holder.button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int i = opr.increment();
                holder.textView2.setText("" + i);
                opr.setCount(i);
                pizza.setQuantity(i);
               holder.button3.setVisibility(View.VISIBLE);
            }

        });

        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean isinserted = helper.insertData(name, price, pizza.getQuantity(), imgid);
    // boolean isinserted2 = helper2.insertData(name, price, pizza.getQuantity(), imgid);
                    if (isinserted)
                        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(context, "Select quantity", Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class view_holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        Button button1;
        Button button2;
        Button button3;
        public view_holder(@NonNull View itemView) {
            super(itemView);
            button1 = itemView.findViewById(R.id.l_bttn1);
            button2 = itemView.findViewById(R.id.l_bttn2);
          imageView = itemView.findViewById(R.id.iv1);
             textView1 = itemView.findViewById(R.id.l_tv1);
             textView2 = itemView.findViewById(R.id.l_tv2);
             textView3 = itemView.findViewById(R.id.l_tv3);
           textView4 = itemView.findViewById(R.id.L_tv4);
            button3 = itemView.findViewById(R.id.l_bttn3);

        }
    }
    class operation{
        int count =0;
        void setCount(int count){
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        int increment(){
            this.count++;
            return count;
        }
        int decrement(){
            this.count--;
           return count ;
        }
    }

}