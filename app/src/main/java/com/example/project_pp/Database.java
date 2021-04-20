package com.example.project_pp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from " + DATABASE_table_1, null
        );
        return cursor.getCount();
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
}
