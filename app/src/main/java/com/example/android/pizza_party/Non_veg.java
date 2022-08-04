package com.example.android.pizza_party;
import com.example.android.pizza_party.Adapters.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Non_veg#} factory method to
 * create an instance of this fragment.
 */
public class Non_veg extends Fragment {

    int i = 0, sum = 0;

    public Non_veg() {
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.list_view, container, false);

            final ArrayList<Pizza> arrayList = new ArrayList<>();
            arrayList.add(new Pizza("Chicken sausages", 100,R.drawable.chickensausage));
            arrayList.add(new Pizza("NonVeg Supreme", 140,R.drawable.nonvegsupreme));
            arrayList.add(new Pizza("Deluxe NonVeg Supreme", 150,R.drawable.deluxenonvegsupreme));
            arrayList.add(new Pizza("Chicken N Corn Delight ", 150,R.drawable.chickenandcorndelight));
            arrayList.add(new Pizza("Tandoori Chicken Pizza", 199,R.drawable.tandoorichickenpizza));
            arrayList.add(new Pizza("Italian Chicken Feast", 209,R.drawable.italianchickenfeast));
            arrayList.add(new Pizza("Chessy Chicken Pizza", 219,R.drawable.chessychickenpizza));
            arrayList.add(new Pizza("Chicken Cheese Burst", 299,R.drawable.chickencheeseburst));


            recycler_Adapter adapter = new recycler_Adapter(arrayList, getContext());
            RecyclerView recyclerView = rootView.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }
}