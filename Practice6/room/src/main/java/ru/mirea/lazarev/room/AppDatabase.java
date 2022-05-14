package ru.mirea.lazarev.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ru.mirea.lazarev.room.EmployeeDao employeeDao();
}