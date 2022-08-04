package com.example.android.pizza_party;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    private String mUsername;
    public static final String ANONYMOUS = "anonymous";
    private static final int  RC_SIGN_IN =1 ;
    CheckBox english;
    CheckBox hindi;
    Button continuButton;
    EditText addressText;
    EditText phoneText;
    EditText shopId;
Button signInButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        mUsername = ANONYMOUS;
        signInButton1 = findViewById(R.id.signInButton);
        shopId = findViewById(R.id.shopId);
        signInButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        english = findViewById(R.id.cb1);
         hindi = findViewById(R.id.hindi);
         continuButton = findViewById(R.id.b_continue);

         addressText = findViewById(R.id.addressedit);
         phoneText = findViewById(R.id.phone_number);
            SharedPreferences sharedPreferences = getSharedPreferences("MyPref",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            String addr = sharedPreferences.getString("firsttime","");
            if(addr.equals("yes")) {
            startActivity(new Intent(MainActivity.this,second_Page.class));
            }
            if(addr.equals("No")) {
            startActivity(new Intent(MainActivity.this, SSshowPlacedOrder.class));
             }
            else {

            continuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (english.isChecked() && hindi.isChecked()){
                        Toast.makeText(MainActivity.this, "Select one language", Toast.LENGTH_SHORT).show();
                    }
                       String addr_User = addressText.getText().toString();
                      String phn_User = phoneText.getText().toString();
                      if(english.isChecked() || hindi.isChecked()) {
                          String shop = shopId.getText().toString();
                          editor.putString("shopId",shop);
                          editor.putString("firsttime", "No");
                          editor.apply();
                          if(sharedPreferences.getString("shopId","").equals("aditya@123")){

                              startActivity(new Intent(MainActivity.this, SSshowPlacedOrder.class));
                              return;
                          }
                          if (addr_User.equals("") || phn_User.equals("")) {
                              Toast.makeText(MainActivity.this, "Fill the details!", Toast.LENGTH_SHORT).show();
                          } else {
                              editor.putString("Phone", phn_User);
                              editor.putString("Address", addr_User);
                              editor.putString("firsttime", "yes");
                              editor.commit();
                              editor.apply();
                              if (english.isChecked()) {
                                  Toast.makeText(MainActivity.this, "Welcome to Pizza Party", Toast.LENGTH_SHORT).show();
                              }
                              if (hindi.isChecked()) {
                                  Toast.makeText(MainActivity.this, "पिज्जा पार्टी में आपका स्वागत है।", Toast.LENGTH_SHORT).show();
                              }
                              Intent intent = new Intent(MainActivity.this, second_Page.class);
                              startActivity(intent);
                          }
                      }
                    else
                        Toast.makeText(MainActivity.this, "Select one language", Toast.LENGTH_SHORT).show();
                }
            });
        }
        }



    private void signIn() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =  firebaseAuth.getCurrentUser();
                if(user!=null)
                    onSignedInInitialize(user.getDisplayName());
                else{
                    //user signed out so
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build())).build(),RC_SIGN_IN);
                }
            }
        };firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                continuButton.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        firebaseAuth.addAuthStateListener(authStateListener);
    }
    private void onSignedInInitialize(String username) {
        mUsername = username;

    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;

    }


}