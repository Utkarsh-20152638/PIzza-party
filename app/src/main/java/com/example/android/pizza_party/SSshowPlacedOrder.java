package com.example.android.pizza_party;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.pizza_party.Adapters.SSorderPlacedAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;

public class SSshowPlacedOrder extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mdatabaseReference;
    SSorderPlacedAdapter adapter;
    ChildEventListener mchildEventListener;
    ArrayList<Pizza> list;
    public static final String FRIENDLY_MSG_LENGTH_KEY = "friendly_msg_length";
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_placed_order);
        firebaseDatabase = FirebaseDatabase.getInstance();

        mdatabaseReference = firebaseDatabase.getReference().child("orders");

        ListView listView = findViewById(R.id.SPOListView);
       list = new ArrayList<>();
        adapter = new SSorderPlacedAdapter(this, R.layout.ss_placedorders, list);
        listView.setAdapter(adapter);
        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
   GenericTypeIndicator<ArrayList<Pizza>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<Pizza>> () {};
                    ArrayList<Pizza> listpizza =  dataSnapshot.getValue(genericTypeIndicator);
                    int i =listpizza.size ();
                    for(int j=0;j<i;j++){

                        list.add(listpizza.get (j));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }




}