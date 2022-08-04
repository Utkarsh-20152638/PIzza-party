package com.example.android.pizza_party;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.android.pizza_party.Adapters.Pager_Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class second_Page extends AppCompatActivity {
 TabLayout tabLayout;
 ViewPager viewPager;
 FirebaseAuth firebaseAuth;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__page);
        tabLayout = (TabLayout) findViewById(R.id.tab1);
        viewPager = (ViewPager) findViewById(R.id.view1);
    Pager_Adapter adapter = new Pager_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        firebaseAuth = FirebaseAuth.getInstance ();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mwnu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.f_order:
               startActivity(new Intent(second_Page.this,DetailView.class));
               break;
            case R.id.moreOption:
                startActivity(new Intent(second_Page.this, orderHistory.class));
                break;
            case R.id.contactUS:
                startActivity(new Intent(second_Page.this, contactUs.class));
                break;
            case R.id.devDesk:
                startActivity(new Intent(second_Page.this, dev_Desk.class));
                break;
            case R.id.profile:
                startActivity(new Intent(second_Page.this, MyProfile.class));
                break;
            case R.id.signOut:
                SharedPreferences sharedPreferences = getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear ();
                editor.apply ();
                firebaseAuth.signOut ();
                startActivity(new Intent(second_Page.this, MainActivity.class));
                finish ();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onDestroy() {
        Dbhelper dbhelper = new Dbhelper(this);
        dbhelper.dropTable();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Pizza misses you")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Dbhelper dbhelper = new Dbhelper(second_Page.this);
                        dbhelper.dropTable();
                    }
                })
                .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(second_Page.this, "Exit option was asked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    }
                })
                .setTitle("Want to close app").show();
    }
}