package com.example.android.pizza_party;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Payment_Activity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    Dbhelper dbhelper;
    public static final String GPAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
   // EditText name, upiId, amount, note;
    String name;
    Button upi;
    String msg;
    Button cod;
    String upiId;
    final int PAY_REQUEST = 1;
    Uri uri;
    String approvalRefNo;
    ArrayList<Pizza> list;
    Button whatsapp;
    // public static String payerName, UpiId, msgNote, sendAmount, status;
    int size;
    Dbhelper2 dbhelper2 = new Dbhelper2(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
       int amt = intent.getIntExtra("Price",1);
       TextView totalMoney = findViewById(R.id.priceEditTextView);
        totalMoney.setText(""+amt);
       //Toast.makeText(Payment_Activity.this, ""+amt, Toast.LENGTH_SHORT).show();
       firebaseDatabase = FirebaseDatabase.getInstance();
       databaseReference = firebaseDatabase.getReference("orders");

       databaseReference2 = firebaseDatabase.getReference().child("paidorders");
       dbhelper = new Dbhelper(this);
       list = dbhelper.getOrders();

        size = list.size();

       cod = findViewById(R.id.homeDelivery);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        String address = sharedPreferences.getString("Address","Address not given");
        String phone = sharedPreferences.getString("Phone","");
        for(int j=0;j<size;j++){
            list.get(j).setAddress (address+"--"+phone);
        }
        name = "Aditya";
        upiId = "7485040890@ybl";
      String  amount = ""+amt;
      msg = "Paying to PizzaParty";
        String refId = "asj ";
      String  tnid = "adsasd";
        upi = findViewById(R.id.upi);

        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.isEmpty() || upiId.isEmpty()){
                    Toast.makeText(Payment_Activity.this, "Name and Upi Id is necessary", Toast.LENGTH_SHORT).show();
                }else PayUsingUpi(name,upiId,amount,msg,tnid,refId);
            }
        });
        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                databaseReference.push().setValue(list);
                databaseReference.push ().setValue(list);
              //  databaseReference.push().setValue(address);
                for(int i=0;i<size;i++){
                    Pizza pizza = list.get(i);
                    dbhelper2.insertData(pizza.getName(),pizza.getPrice(),pizza.getQuantity(),pizza.getImgid());
                }

                startActivity(new Intent(Payment_Activity.this,OrderPlaced.class));
                Dbhelper dbhelper = new Dbhelper(Payment_Activity.this);
                dbhelper.dropTable();
            }
        });
        whatsapp = findViewById(R.id.whatsapp);
       whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    //   Toast.makeText(Payment_Activity.this, "this feature will be added after payment", Toast.LENGTH_LONG).show();
                if (true){
                String phonestr ="+916200293499";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+phonestr+
                            "&text="+""));
                    startActivity(i);
                }else {
                Toast.makeText(Payment_Activity.this,"Whatsapp is not installed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
  /*  whatsapp intent to check if it is installed or not
  private boolean isWhatappInstalled(String s){
        PackageManager packageManager = getPackageManager();
        boolean whatsappInstalled;
        try {
            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            whatsappInstalled = true;
        }catch (PackageManager.NameNotFoundException e){
            whatsappInstalled = false; }
        return whatsappInstalled; }*/
    private void PayUsingUpi(String name,String upiId,String amt,String msg, String trnId, String refId){
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("mc", "")  //  use it
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("tr", "25584584") // use it
                .appendQueryParameter("tn", msg)
                .appendQueryParameter("am", amt)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiIntent = new Intent(Intent.ACTION_VIEW);
        upiIntent.setData(uri);
        Intent chooser = Intent.createChooser(upiIntent,"Pay");
        if(chooser.resolveActivity(getPackageManager()) != null){
            startActivityForResult(chooser,PAY_REQUEST);
        }else{
            Toast.makeText(this, "No UPI app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PAY_REQUEST){

            if(isInternetAvailabe(Payment_Activity.this)){

                if (data == null) {
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    String temp = "nothing";
                    Toast.makeText(this, "Transaction not complete", Toast.LENGTH_SHORT).show();
                }else {
                    String text = data.getStringExtra("response");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add(text);

                    upiPaymentCheck(text);
                }
            }

        }
    }

    void upiPaymentCheck(String data){
        String str = data;

        String payment_cancel = "";
        String status = "";
        String response[] = str.split("&");

        for (int i = 0; i < response.length; i++)
        {
            String equalStr[] = response[i].split("=");
            if(equalStr.length >= 2)
            {
                if (equalStr[0].toLowerCase().equals("Status".toLowerCase()))
                {
                    status = equalStr[1].toLowerCase();
                }
            }
            else
            {
                payment_cancel = "Payment cancelled";
            }
        }
        if(status.equals("success")){
           // Toast.makeText(this, "Transaction Successfull", Toast.LENGTH_SHORT).show();
            databaseReference2.push().setValue(list);
            for(int i=0;i<size;i++){
                Pizza pizza = list.get(i);
                dbhelper2.insertData(pizza.getName(),pizza.getPrice(),pizza.getQuantity(),pizza.getImgid());
            }
            startActivity(new Intent(Payment_Activity.this,OrderPlaced.class));

        }else if("Payment cancelled".equals(payment_cancel)){
            Toast.makeText(this, "payment cancelled by user", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean isInternetAvailabe(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo.isConnected() && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable()){
                return true;
            }
        }
        return false;
    }
}