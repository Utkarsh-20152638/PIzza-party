package com.example.android.pizza_party;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText addr;
    EditText phone;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        addr = findViewById(R.id.MPeditText1);
        phone = findViewById(R.id.MP_editPhone);
        save = findViewById(R.id.MPbutton2);

        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String address = sharedPreferences.getString("Address","Not given");
        String SPphone = sharedPreferences.getString("Phone","Not given");
        addr.setText(""+address);
        phone.setText(""+SPphone);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addre = addr.getText().toString();
                String num = phone.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putString("Phone", num);
                editor.putString("Address", addre);
                editor.putString("firsttime", "yes");
                editor.commit();
                editor.apply();
                Toast.makeText(MyProfile.this, "Data Saved", Toast.LENGTH_SHORT).show();
                MyProfile.this.finish();
            }
        });
    }
}