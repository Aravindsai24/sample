package com.example.sample;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.sample.modal.Word;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);
}
