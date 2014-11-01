package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 10/28/14.
 */
public class MySQLite extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BirdDB";

    // Defining DB
    // Btable
    // | id | name  | location  | Bird Images   | Bird Sound    |
    // |  0 | Bird1 |   MY      |               |               |
    // |  1 | Bird2 |   SG      |               |               |
    //

    public static final String TABLE_NAME = "BTable";
    public static final String ID = "id";
    public static final String COLUMN_NAME_ENTRY_ID = "name";
    public static final String BIRD_IMAGES = "image";
    public static final String BIRD_SOUND = "sound";

    public static final String COLUMN_NAME_TITLE = "location";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MySQLite.TABLE_NAME + " (" +
                    MySQLite.ID + " INTEGER PRIMARY KEY," +
                    MySQLite.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    MySQLite.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    MySQLite.BIRD_IMAGES + TEXT_TYPE + COMMA_SEP+
                    MySQLite.BIRD_SOUND+ TEXT_TYPE+

                    " )";

    public MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS "+MySQLite.TABLE_NAME);


        // create fresh books table
        this.onCreate(db);
    }


    // Add Database
    public void addBird(Bird bird){
        Log.d("addBird", bird.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLite.COLUMN_NAME_ENTRY_ID, bird.name);
        values.put(MySQLite.COLUMN_NAME_TITLE, bird.location);
        values.put(MySQLite.BIRD_IMAGES, bird.image);
        values.put(MySQLite.BIRD_SOUND, bird.sound);

        db.insert(TABLE_NAME,null,values);


        db.close();
    }

    // Get All Bird
    public List<Bird> getAllBird(){
        List<Bird> birds = new LinkedList<Bird>();

        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Loop through the database
        Bird bird = null;
        if (cursor.moveToFirst()){
            do {
                bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setName(cursor.getString(1));
                bird.setLocation(cursor.getString(2));
                bird.setImage(cursor.getString(3));
                bird.setSound(cursor.getString(4));
                birds.add(bird);
            } while(cursor.moveToNext());

        }

        Log.d("getAllBirds()", birds.toString());

        return birds;
    }

    // Clean Database
    public void cleanDB (){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM " + MySQLite.TABLE_NAME);

    }


    // Read Database and returns class associated with the query
    public List<Bird> getBird(String Query, String Column){
        List<Bird> birds = new LinkedList<Bird>();
        SQLiteDatabase db=this.getReadableDatabase();
        // Db exec : Search Table for Entry Related to Query of a Specified Column
        String DbCommand ="SELECT  * FROM " + TABLE_NAME+" WHERE "+ Column + "=" + "\"" + Query+ "\"" ;
        Cursor cursor = db.rawQuery(DbCommand,null);

        Bird bird = null;
        if (cursor.moveToFirst()){
            do {
                bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setName(cursor.getString(1));
                bird.setLocation(cursor.getString(2));
                bird.setImage(cursor.getString(3));
                bird.setSound(cursor.getString(4));
                birds.add(bird);
            } while(cursor.moveToNext());

        }

        Log.d("getBird(" + Query+", " + Column + ")", birds.toString());

        return birds;
    }


}
