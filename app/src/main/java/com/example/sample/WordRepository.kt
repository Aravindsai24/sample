package com.example.sample

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.sample.modal.Word

class WordRepository {
    private lateinit var myWordDao: WordDao
    private lateinit var myAllWords: LiveData<List<Word>>

    fun WordRepository(application: Application?) {
        val db: WordRoomDB = WordRoomDB.getDatabase(application)
        myWordDao = db.wordDao()
        myAllWords = myWordDao.getAllWords()
    }

}