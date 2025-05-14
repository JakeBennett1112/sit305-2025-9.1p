package com.example.passtask91.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.passtask91.model.Advert;
import com.example.passtask91.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    // creating database if it doesn't exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table
        // Pass in the objects
        String CREATE_ITEM_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.ITEM_NAME + " TEXT, "
                + Util.ITEM_DESCRIPTION + " TEXT, "
                + Util.ITEM_DATE + " TEXT, "
                + Util.ITEM_LOCATION + " TEXT, "
                + Util.ITEM_PHONE + " INTEGER)"
                ;
        // execute the query
        db.execSQL(CREATE_ITEM_TABLE);

    }
    // making changes to our database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the table
        String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_ITEM_TABLE);

        onCreate(db);
    }

    // insert new item
    public long insertItem(Advert item)
    {
        SQLiteDatabase db = this.getWritableDatabase(); // write into database
        ContentValues contentValues = new ContentValues();
        // Put information into database by getting items from advert table using getters and setters
        contentValues.put(Util.ITEM_DESCRIPTION, item.getItem_description());
        contentValues.put(Util.ITEM_DATE, item.getItem_date());
        contentValues.put(Util.ITEM_LOCATION, item.getItem_location());
        contentValues.put(Util.ITEM_PHONE, item.getItem_phone());


        // insert into the table
        long newRowID = db.insert(Util.TABLE_NAME, null, contentValues);

        // close database after opening to prevent memory leaks
        db.close();;
        return newRowID;
    }

    public boolean fetchItem(String item_date, String item_location) {
        // want to read and not change
        SQLiteDatabase db = this.getReadableDatabase();
        // cursor so we can iterate through the database
        // go through all the rows and check data and location
        // if item exists return item_name
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.ITEM_NAME}, Util.ITEM_DATE + "=? and " + Util.ITEM_LOCATION + "=?",
                new String[]{item_date, item_location}, null, null, null);
        int numberOfRows = cursor.getCount(); // checking number of rows
        db.close();

        if (numberOfRows > 0) // the item exists
        {
            return true;
        } else
        {
            return false;
        }
    }

    // retrieve all records from the database and return them as a list of advert objects
    public List<Advert> getAllItems() {
        List<Advert> itemList = new ArrayList<>(); // create an empty list to hold advert objects
        SQLiteDatabase db = this.getReadableDatabase(); // Open the database in read only mode

        // Utilise SQL Queries to get all items from the table
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        // move cursor to first result
        if (cursor.moveToFirst()) {
            do {
                Advert advert = new Advert(); // create a new advert for each record in the database
                // Set object propeties
                advert.setItem_name(cursor.getString(cursor.getColumnIndexOrThrow(Util.ITEM_NAME)));
                advert.setItem_description(cursor.getString(cursor.getColumnIndexOrThrow(Util.ITEM_DESCRIPTION)));
                advert.setItem_date(cursor.getString(cursor.getColumnIndexOrThrow(Util.ITEM_DATE)));
                advert.setItem_location(cursor.getString(cursor.getColumnIndexOrThrow(Util.ITEM_LOCATION)));
                advert.setItem_phone(cursor.getInt(cursor.getColumnIndexOrThrow(Util.ITEM_PHONE)));

                itemList.add(advert); // add avert to the list
            } while (cursor.moveToNext()); // move to  next result
        }

        // close the cursor and database
        cursor.close();
        db.close();
        // return the list of adverts
        return itemList;
    }

    // Create delete item by name for removing an item
    public void deleteItemByName(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.ITEM_NAME + "=?", new String[]{item});
        db.close();
    }
}
