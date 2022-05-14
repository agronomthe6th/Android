package ru.mirea.lazarev.mireaproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String imageUri;

    public String getUri(){
        return this.imageUri;
    }
}
