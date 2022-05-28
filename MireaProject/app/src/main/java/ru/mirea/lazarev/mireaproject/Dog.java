package ru.mirea.gribkova.mireaproject1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dog {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String breed;

    public String name_dog;
}
