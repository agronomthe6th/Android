package ru.mirea.gribkova.mireaproject1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DogDao dogDao();

    private static ru.mirea.gribkova.mireaproject1.AppDatabase INSTANCE;

    public static ru.mirea.gribkova.mireaproject1.AppDatabase getDbInstance(Context context){
        if (INSTANCE== null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ru.mirea.gribkova.mireaproject1.AppDatabase.class, "BD_DOGS")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
