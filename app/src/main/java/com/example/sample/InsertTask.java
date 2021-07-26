package com.example.sample;

import android.os.AsyncTask;

import com.example.sample.modal.Word;

public class InsertTask extends AsyncTask<Void,Void,Void> {
    WordDao mWordDao;
    Word mword;

    public InsertTask(WordDao wordDao, Word word){
        mword =word;
        mWordDao = wordDao;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        mWordDao.insert(mword);
        return null;
    }
}
