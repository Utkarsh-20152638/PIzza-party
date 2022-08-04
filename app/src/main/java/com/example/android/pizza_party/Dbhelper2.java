package com.example.android.pizza_party;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Dbhelper2 extends SQLiteOpenHelper {
    private final static String name = "mydatabase2.db";
    final static int version = 3;
    public Dbhelper2(@Nullable Context context ) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table ordershistory" +
                        "(id integer primary key autoincrement,"+
                        "f_name text,"+
                        "price int,"+
                        "image int,"+
                        "quantity int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists ordershistory");
        onCreate(db);
    }
    public boolean insertData(String name ,int price ,int quantity,int imgid){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name",name);
        contentValues.put("price",price);
        contentValues.put("quantity",quantity);
        contentValues.put("image",imgid);
        long id = database.insert("ordershistory",null,contentValues);
        if(id<=0)
            return false;
        else
            return true;
    }
    public ArrayList<Pizza> getOrdershistory(){
        ArrayList<Pizza> list = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,f_name,price,image,quantity from ordershistory",null);
        if(cursor.moveToFirst()){
            do {
                Pizza pizza = new Pizza();
                pizza.setId(cursor.getInt(0));
                pizza.setName(cursor.getString(1)+"");
                pizza.setPrice(cursor.getInt(2));
                pizza.setImgid(cursor.getInt(3));
                pizza.setQuantity(cursor.getInt(4));
                list.add(pizza);
            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return list;
    }
    public Cursor getOrderbyId(int id){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from ordershistory where id="+id,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
    public boolean updateOrder(String name ,int price ,int quantity,int id,int imgid){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name",name);
        contentValues.put("price",price);
        contentValues.put("quantity",quantity);
        contentValues.put("image",imgid);
        long row = database.update("ordershistory",contentValues,"id="+id,null);
        if(row<=0)
            return false;
        else
            return true;
    }
    public int deletes(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.delete("ordershistory","id="+id,null);
    }
    public void dropTable(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("drop table if exists ordershistory");
        onCreate(sqLiteDatabase);
    }

}

