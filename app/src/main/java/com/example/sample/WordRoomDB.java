package com.example.sample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sample.modal.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDB extends RoomDatabase {
    public abstract WordDao wordDao();
    private static WordRoomDB INSTANCE;
    static WordRoomDB getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (WordRoomDB.class){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDB.class,"word_database")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
