package com.example.votingapp;

import static com.example.votingapp.Constants.COL_CANDIDATE_ID;
import static com.example.votingapp.Constants.COL_ID;
import static com.example.votingapp.Constants.COL_NAME;
import static com.example.votingapp.Constants.VOTES_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbHandler extends SQLiteOpenHelper {
    public dbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + VOTES_TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY," +
                COL_NAME + " TEXT," +
                COL_CANDIDATE_ID + " INTEGER" + ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VOTES_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addVote(Vote vote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, vote.getName());
        contentValues.put(COL_CANDIDATE_ID, vote.getCandidate_ID());
        db.insert(VOTES_TABLE, null, contentValues);
        db.close();
    }
    public Vote getVote(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + VOTES_TABLE + " WHERE " + COL_ID + " = '"+ id +"'", null);

        if (cursor.moveToFirst()) {
            return new Vote(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getInt(2)
            );
        }
        return null;
    }

    public Vote getVote (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + VOTES_TABLE + " WHERE " + COL_NAME + " = '"+ name +"'", null);

        if (cursor.moveToFirst()) {
            return new Vote(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getInt(2)
            );
        }
        return null;
    }

    public ArrayList<Vote> getAllVotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Vote> voteArrayList = new ArrayList<Vote>();
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + VOTES_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                voteArrayList.add(new Vote(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getInt(2)
                ));
            } while (cursor.moveToNext());
            return voteArrayList;
        }

        return null;
    }

    public Integer getVoteAmount(int candidate_ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + VOTES_TABLE + " WHERE " + COL_CANDIDATE_ID + " = '"+ candidate_ID +"'", null);
        return cursor.getCount();
    }
}
