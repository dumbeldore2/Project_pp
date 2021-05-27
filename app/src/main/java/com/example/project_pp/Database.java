package com.example.project_pp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    //tabel 3
    public static final String DATABASE_table_3 = "websites_new";
    public static final String Table_3_col_1 = "website_id_new";
    public static final String Table_3_col_2 = "website_naam_new";

    //tabel 4
    public static final String DATABASE_table_4 = "codes_new";
    public static final String Table_4_col_1 = "codes_id_new";
    public static final String Table_4_col_2 = "website_id_new";
    public static final String Table_4_col_3 = "codes_email_new";
    public static final String Table_4_col_4 = "codes_code_new";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_table_1 + "(" + Table_1_col_1 + " INTEGER primary key," + Table_1_col_2 + " TEXT)");
        db.execSQL("create table " + DATABASE_table_2 + "(" + Table_2_col_1 + " INTEGER primary key," + Table_2_col_2 + " INTEGER ," + Table_2_col_3 + " TEXT ," + Table_2_col_4 + " Text , foreign key (" + Table_2_col_2 + ") references " + DATABASE_table_1 + "(" + Table_1_col_1 + "))");
        db.execSQL("create table " + DATABASE_table_3 + "(" + Table_3_col_1 + " INTEGER primary key," + Table_3_col_2 + " TEXT)");
        db.execSQL("create table " + DATABASE_table_4 + "(" + Table_4_col_1 + " INTEGER primary key," + Table_4_col_2 + " INTEGER ," + Table_4_col_3 + " TEXT ," + Table_4_col_4 + " Text , foreign key (" + Table_4_col_4 + ") references " + DATABASE_table_3 + "(" + Table_3_col_1 + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_table_1);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_table_2);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_table_3);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_table_4);
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

    public ArrayList<String> namen() {

        ArrayList<String> uits = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_1_col_2 + " from " + DATABASE_table_1 + "", null);

        for (int i = 0; i <= cursor.getCount(); i++) {
            if (cursor.moveToPosition(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cursor.getString(0));
                uits.add(stringBuffer.toString());
            }
        }
        return uits;
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

        int[] ids = idsTableTwo(id);
        for(int i = 0 ; i < ids.length ; i ++){
            deleteTable2Row(ids[i]);
        }
    }

    public void rename(int id, String naam){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table_1_col_1,id);
        contentValues.put(Table_1_col_2,naam);
        sqLiteDatabase.update(DATABASE_table_1,contentValues,Table_1_col_1 + " == ?", new String[] {id +""});
    }

    public boolean uniqueWebsite(String website){
        boolean uit = false;
        ArrayList<String> webs = namen();
        ArrayList<String> webs2 = namen3();
        if (webs.contains(website)){
            if (webs2.contains(website)){
                uit = true;
            }
        }
        System.out.println(uit);
        return uit;
    }




    ///tabel 2

    public int IDMAKERTABLE2() {

        int uit = -1;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from " + DATABASE_table_2, null
        );
        if (cursor.getCount() == 0){
            uit = 0;
        } else {
            Cursor cursor1 = sqLiteDatabase.rawQuery("select max(" + Table_2_col_1 + " ) from " + DATABASE_table_2 + "" ,null);
            StringBuffer stringBuffer = new StringBuffer();
            if (cursor1.moveToFirst()){
                stringBuffer.append(cursor1.getString(0));
                uit = Integer.parseInt(stringBuffer.toString()) +1;
            }
        }
        return uit;
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
                    sqLiteDatabase.insert(DATABASE_table_2, null, contentValues);
            }
        }
    }


    public int[] idsTableTwo(int id) {

        int uit[];

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_2_col_1 + " from " + DATABASE_table_2 + " where " + Table_2_col_2 + " == " + id + "", null);

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

    public String getTable_2_col_3(int pid){
        String uit = "";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_2_col_3 + " from " + DATABASE_table_2 + " where " + Table_2_col_1 + " == " + pid + "",null);
        if (cursor.moveToFirst()){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cursor.getString(0));
            uit = stringBuffer.toString();
        }
        return uit;
    }

    public String getTable_2_col_4(int pid){
        String uit = "";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_2_col_4 + " from " + DATABASE_table_2 + " where " + Table_2_col_1 + " == " + pid + "",null);
        if (cursor.moveToFirst()){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cursor.getString(0));
            uit = stringBuffer.toString();
        }
        return uit;
    }

    public void deleteTable2Row(int pid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_table_2 , ""+ Table_2_col_1 + " == ?" , new String[]{pid +""});
    }

    public void rename2(int pid,int id, String email, String code){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table_2_col_1,pid);
        contentValues.put(Table_2_col_2,id);
        contentValues.put(Table_2_col_3,email);
        contentValues.put(Table_2_col_4,code);
        sqLiteDatabase.update(DATABASE_table_2,contentValues,Table_2_col_1 + " == ?", new String[] {pid +""});
    }

    public boolean uniqueEmail(String email , int id){
        boolean uit = true;
        String[] emails = emails(id).clone();
        for (int i = 0; i < emails.length && uit ; i++){
            if (email.equals(emails[i])){
                uit = false;
            }
        }
        System.out.println(uit);
        return uit;
    }


    // Alle code omwile de derde tabel

    public int IDMAKERTABLE3() {

        int uit = -1;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from " + DATABASE_table_3, null
        );
        if (cursor.getCount() == 0){
            uit = 0;
        } else {
            Cursor cursor1 = sqLiteDatabase.rawQuery("select max(" + Table_3_col_1 + " ) from " + DATABASE_table_3 + "" ,null);
            StringBuffer stringBuffer = new StringBuffer();
            if (cursor1.moveToFirst()){
                stringBuffer.append(cursor1.getString(0));
                uit = Integer.parseInt(stringBuffer.toString()) +1;
            }
        }
        return uit;
    }


    public void addToTabel3(String naam) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!naam.trim().isEmpty() && naam != null) {
            contentValues.put(Table_3_col_1, IDMAKERTABLE3());
            contentValues.put(Table_3_col_2, naam);
        }

        sqLiteDatabase.insert(DATABASE_table_3, null, contentValues);
    }

    public ArrayList<String> namen3() {

        ArrayList<String> uits = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_3_col_2 + " from " + DATABASE_table_3 + "", null);

        for (int i = 0; i <= cursor.getCount(); i++) {
            if (cursor.moveToPosition(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cursor.getString(0));
                uits.add(stringBuffer.toString());
            }
        }
        return uits;
    }

    /*public int[] idsTable3() {

        int uit[];

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + Table_3_col_1 + " from " + DATABASE_table_3 + "", null);

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

    public String getTable_3_col_2(int id){
        String uit = "";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " +Table_3_col_2+ " from " + DATABASE_table_3 + " where " +Table_3_col_1+ " == " + id + "" ,null);
        if (cursor.moveToFirst()){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cursor.getString(0));
            uit = stringBuffer.toString();
        }
        return uit;
    }

    public void deleteTable3Row(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_table_3 , ""+ Table_3_col_1 + " == ?" , new String[]{id +""});

        int[] ids = idsTable3();
        for(int i = 0 ; i < ids.length ; i ++){
            deleteTable2Row(ids[i]);
        }
    }

    public void rename(int id, String naam){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table_1_col_1,id);
        contentValues.put(Table_1_col_2,naam);
        sqLiteDatabase.update(DATABASE_table_1,contentValues,Table_1_col_1 + " == ?", new String[] {id +""});
    }*/
}
