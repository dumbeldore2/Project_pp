package com.example.project_pp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import javax.xml.transform.sax.TemplatesHandler;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database_project_pp_mobile_app.db";
    //tabel 1
    public static final String DATABASE_table_1 = "websites";
    public static final String Table_1_col_1 = "website_id";
    public static final String Table_1_col_2 = "website_naam";

    //tabel 2
    public static final String DATABASE_table_2 = "codes";
    public static final String Table_2_col_1 = "codes_id";
    public static final String Table_2_col_2 = "website_id";
    public static final String Table_2_col_3 = "codes_email";
    public static final String Table_2_col_4 = "codes_code";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_table_1 + "(" + Table_1_col_1 + " INTEGER primary key," + Table_1_col_2 + " TEXT)");
        db.execSQL("create table " + DATABASE_table_2 + "(" + Table_2_col_1 + " INTEGER primary key," + Table_2_col_2 + " INTEGER ," + Table_2_col_3 + " TEXT ," + Table_2_col_4 + " Text , foreign key (" + Table_2_col_2 + ") references " + DATABASE_table_1 + "(" + Table_1_col_1 + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_table_1);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_table_2);
    }

    // Alle code omwile de eerste tabel

    public int IDMAKERTABLE1() {

        int uit = -1;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from " + DATABASE_table_1, null
        );
        if (cursor.getCount() == 0){
            uit = 0;
        } else {
            Cursor cursor1 = sqLiteDatabase.rawQuery("select max(" + Table_1_col_1 + " ) from " + DATABASE_table_1 + "" ,null);
            StringBuffer stringBuffer = new StringBuffer();
            if (cursor1.moveToFirst()){
                stringBuffer.append(cursor1.getString(0));
                uit = Integer.parseInt(stringBuffer.toString()) +1;
            }
        }

        System.out.println(uit);
        return uit;
    }


    public void addToTabel1(String naam) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!naam.trim().isEmpty() && naam != null) {
            contentValues.put(Table_1_col_1, IDMAKERTABLE1());
            contentValues.put(Table_1_col_2, naam);
        }

        sqLiteDatabase.insert(DATABASE_table_1, null, contentValues);
    }

    public String[] namen() {

        String uit[];

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_1_col_2 + " from " + DATABASE_table_1 + "", null);

        uit = new String[cursor.getCount()];

        for (int i = 0; i <= cursor.getCount(); i++) {
            if (cursor.moveToPosition(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cursor.getString(0));
                uit[i] = stringBuffer.toString();
            }
        }

        return uit;
    }

    public int[] idsTableOne() {

        int uit[];

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_1_col_1 + " from " + DATABASE_table_1 + "", null);

        uit = new int[cursor.getCount()];

        for (int i = 0; i <= cursor.getCount(); i++) {
            if (cursor.moveToPosition(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cursor.getString(0));
                uit[i] = Integer.parseInt(stringBuffer.toString());
            }
        }

        return uit;
    }

    public String getTable_1_col_2(int id){
        String uit = "";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " +Table_1_col_2+ " from " + DATABASE_table_1 + " where " +Table_1_col_1+ " == " + id + "" ,null);
        if (cursor.moveToFirst()){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cursor.getString(0));
            uit = stringBuffer.toString();
        }
        return uit;
    }

    public void deleteTable1Row(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_table_1 , ""+ Table_1_col_1 + " == ?" , new String[]{id +""});
    }

    public void rename(int id, String naam){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table_1_col_1,id);
        contentValues.put(Table_1_col_2,naam);
        sqLiteDatabase.update(DATABASE_table_1,contentValues,Table_1_col_1 + " == ?", new String[] {id +""});
    }




    ///tabel 2

    public int IDMAKERTABLE2() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from " + DATABASE_table_2, null
        );
        return cursor.getCount();
    }

    public void addToTabel2(String email, String code, int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!email.trim().isEmpty() && email != null) {
            if (!code.trim().isEmpty() && code != null){
                contentValues.put(Table_2_col_1, IDMAKERTABLE2());
                contentValues.put(Table_2_col_2, id);
                contentValues.put(Table_2_col_3, email);
                contentValues.put(Table_2_col_4, code);
            }
        }
        sqLiteDatabase.insert(DATABASE_table_2, null, contentValues);
    }


    public String[] emails(int id) {

        String uit[];

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_2_col_3 + " from " + DATABASE_table_2 + " where " + Table_2_col_2 + " == " + id + "", null);

        uit = new String[cursor.getCount()];

        for (int i = 0; i <= cursor.getCount(); i++) {
            if (cursor.moveToPosition(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cursor.getString(0));
                uit[i] = stringBuffer.toString();
            }
        }
        return uit;
    }

    public String[] codes(int id) {

        String uit[];

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_2_col_4 + " from " + DATABASE_table_2 + " where " + Table_2_col_2 + " == " + id + "", null);

        uit = new String[cursor.getCount()];

        for (int i = 0; i <= cursor.getCount(); i++) {
            if (cursor.moveToPosition(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cursor.getString(0));
                uit[i] = stringBuffer.toString();
            }
        }
        return uit;
    }


}
