package com.example.android.pizza_party.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.android.pizza_party.Dbhelper;
import com.example.android.pizza_party.Pizza;
import com.example.android.pizza_party.R;

import java.util.ArrayList;

public class detailview_adapter extends RecyclerView.Adapter<viewHolder> {
  public  int priceTotal;

    ArrayList<Pizza> arrayList;
    Context context;
    public detailview_adapter(ArrayList<Pizza> arrayList, Context context) {
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
        Dbhelper helper = new Dbhelper(context);

        Cursor cursor = helper.getOrderbyId(id);
        int tprice = cursor.getInt(2) * pizza.getQuantity();
        holder.textView4.setText(tprice + "");
           ///// priceTotal += tprice;
       // Toast.makeText(context, ""+priceTotal, Toast.LENGTH_SHORT).show();
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
            //int a =   CalTotPrice(arrayList);
       holder.deleteButtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Dbhelper dbhelper = new Dbhelper(context);
              // dbhelper.deletes(pizza.getId());
               new AlertDialog.Builder(context).setIcon(R.drawable.ic_baseline_delete_24)
                       .setTitle("Want to delete ?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dbhelper.deletes(pizza.getId());
                               arrayList.remove(holder.getAdapterPosition());
                               notifyDataSetChanged();
                           }
                       }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                   }
               }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(context, "Asking to delete the order", Toast.LENGTH_SHORT).show();
                   }
               }).show();


           }
       });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
//  ///////////  public int getTotalPrice(int price){
//      priceTotal += price;
//       Toast.makeText(context,""+priceTotal,Toast.LENGTH_SHORT).show();
//       return priceTotal;
//    }

}

class viewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView1;
    EditText textView2;
    Button button1;
    TextView textView4;
    Button deleteButtn;
    Button button2;
    Button payment;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.d_iv1);
        textView1 = itemView.findViewById(R.id.d_tv1);
        textView2 = itemView.findViewById(R.id.d_tv2);
        button1 = itemView.findViewById(R.id.d_edit);
        payment =itemView.findViewById(R.id.payment);
        deleteButtn = itemView.findViewById(R.id.d_cb1);
        textView4 = itemView.findViewById(R.id.d_tv4);


    }



}
