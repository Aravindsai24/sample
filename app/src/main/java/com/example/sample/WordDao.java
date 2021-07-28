package com.example.sample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sample.modal.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Query("Delete From Word where Word.title = :title")
    void deleteWord(String title);

    @Query("SELECT * FROM Word")
    LiveData<List<Word>> getAllWords();

}
