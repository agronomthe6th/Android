package ru.mirea.gribkova.mireaproject1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DogDao {
    @Query("SELECT * FROM Dog")
    List<Dog> getAll();

    @Query("SELECT * FROM Dog WHERE id = :id")
    Dog getById(long id);

    @Insert
    void insert(Dog employee);

    @Update
    void update(Dog employee);

    @Delete
    void delete(Dog employee);
}
