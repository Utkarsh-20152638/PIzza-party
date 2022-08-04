package com.example.android.pizza_party;
import com.example.android.pizza_party.Adapters.*;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.pizza_party.Adapters.recycler_Adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Veg extends Fragment {
        int i=0,sum=0;
    public Veg() {
    }
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null){
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<Pizza> arrayList = new ArrayList<>();
       // arrayList.add(new Pizza("Chessy",100,R.drawable.margheritacheesepizza));
        arrayList.add(new Pizza("Delux veggie Supreme",140,R.drawable.deluxeveggiesupreme));
        arrayList.add(new Pizza("Delux veggie prime",150,R.drawable.deluxeveggieprime));
        arrayList.add(new Pizza("Veg Mix Pizza ",150,R.drawable.vegmixpizza));
        arrayList.add(new Pizza("Margherita Prime",200,R.drawable.margheritaprime));
        arrayList.add(new Pizza("Veggie Paradise",180,R.drawable.veggieparadise));
        arrayList.add(new Pizza("Delux Veggie",129,R.drawable.deluxeveggie));
        arrayList.add(new Pizza("Onion prime",70,R.drawable.onionprime));
        arrayList.add(new Pizza("Golden corn prime",90,R.drawable.goldencornprime));
        arrayList.add(new Pizza("Love Pizza",200,R.drawable.lovepizza));
        arrayList.add(new Pizza("Paneer N Corn Delight",185,R.drawable.paneerncorndelight));
        arrayList.add(new Pizza("Corn and Jalapeno",150,R.drawable.cornnjalpeno));
        arrayList.add(new Pizza("Cheese Burst",170,R.drawable.cheeseburstpizza));
        arrayList.add(new Pizza("Onion Pizza",80,R.drawable.onionpizza));
       // arrayList.add(new Pizza("Capsicum",199,R.drawable));
       // arrayList.add(new Pizza("VEg Pizza",110,R.drawable.));
        arrayList.add(new Pizza("Margherita/Cheese pizza",120,R.drawable.margheritacheesepizza));
        arrayList.add(new Pizza("Paneer N Onion",125,R.drawable.paneernonion));
        arrayList.add(new Pizza("Veg Loaded",115,R.drawable.vegloaded));
        arrayList.add(new Pizza("Sweet N Corn",225,R.drawable.sweetcornpizza));
      //  arrayList.add(new Pizza("Veg Paneer Pizza",195,R.drawable.));
        arrayList.add(new Pizza("Veg Extravaganza",175,R.drawable.vegextravaganza));

        recycler_Adapter adapter = new recycler_Adapter(arrayList,getContext());
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
         recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);}
        return rootView;
    }



}