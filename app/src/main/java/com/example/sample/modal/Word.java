package com.example.sample.modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    public String title;

    public Word(String title) {
        this.title = title;
    }
}
