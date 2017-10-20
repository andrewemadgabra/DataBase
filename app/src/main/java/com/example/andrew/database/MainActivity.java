package com.example.andrew.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data test = new Data(this);
        test.insertData();
        test.readData();
    }

    public class contract {

        public contract() {
        }

        public class EntryData implements BaseColumns {
            public static final String table_name = "student";
            public static final String column_id = BaseColumns._ID;
            public static final String column_name = "name";
            public static final String column_age = "age";
        }
    }

    public static class Data extends SQLiteOpenHelper {
        private static final String Database_name = "student.db";

        public Data(Context context) {
            super(context, Database_name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlcreatetable = "CREATE TABLE " + contract.EntryData.table_name + "(" + contract.EntryData.column_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + contract.EntryData.column_name + " STRING NOT NULL," + contract.EntryData.column_age + " INTEGER NOT NULL);";
            db.execSQL(sqlcreatetable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
        public void insertData(){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(contract.EntryData.column_name, "Andrew");
            values.put(contract.EntryData.column_age, "19");
            db.insert(contract.EntryData.table_name, null, values);
            ContentValues value1 = new ContentValues();
            value1.put(contract.EntryData.column_name, "Emad");
            value1.put(contract.EntryData.column_age, "20");
            db.insert(contract.EntryData.table_name, null, value1);
        }
        public Cursor readData(){
            SQLiteDatabase readbd =getReadableDatabase();
            String[] output = {contract.EntryData.column_name,contract.EntryData.column_age};
            String id= contract.EntryData.column_id;
            Cursor cursor = readbd.query(contract.EntryData.table_name,output,id,null,null,null,null);
            return cursor;
        }
    }
}
