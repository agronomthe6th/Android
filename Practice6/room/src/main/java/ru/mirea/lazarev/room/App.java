package ru.mirea.lazarev.room;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;
    private ru.mirea.lazarev.room.AppDatabase database;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, ru.mirea.lazarev.room.AppDatabase.class,
                "database").allowMainThreadQueries().build();
    }

    public static App getInstance() {
        return instance;
    }

    public ru.mirea.lazarev.room.AppDatabase getDatabase(){
        return database;
    }
}