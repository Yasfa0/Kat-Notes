package com.example.katnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "KatNotesDB";
    private static  final String TABLE_NAME = "notes_table";
    private static final String KEY_ID = "notes_id";
    private static final String KEY_NOTES = "notes_content";

    public DatabaseHelper(Context context){super(context,DB_NAME,null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String buatTabel = "Create Table " + TABLE_NAME + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOTES + " TEXT )";
        db.execSQL(buatTabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = ("drop table if exists " + TABLE_NAME);
        db.execSQL(drop);
        onCreate(db);
    }

    public List<KatNotes> selectData(){
        ArrayList<KatNotes> notes = new ArrayList<KatNotes>();

        SQLiteDatabase db = getReadableDatabase();
        String [] columns = {KEY_ID,KEY_NOTES};
        Cursor cursor = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String content = cursor.getString(1);

            KatNotes knotes = new KatNotes();
            knotes.setNotesId(id);
            knotes.setNotesContent(content);
            notes.add(knotes);
        }

        return notes;
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String syaratHapus = KEY_ID + " = '" + id + "'";
        db.delete(TABLE_NAME,syaratHapus,null);
    }

    public void insert(KatNotes notes){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID,notes.getNotesId());
        values.put(KEY_NOTES,notes.getNotesContent());

        db.insert(TABLE_NAME,null,values);
    }

    public void getDbtest(){
        SQLiteDatabase db = getWritableDatabase();
    }

    public void update(KatNotes notes){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID,notes.getNotesId());
        values.put(KEY_NOTES,notes.getNotesContent());

        String indikatorHapus = KEY_ID + " = '" + notes.getNotesId() + "'";

        db.update(TABLE_NAME,values,indikatorHapus,null);
    }

}
