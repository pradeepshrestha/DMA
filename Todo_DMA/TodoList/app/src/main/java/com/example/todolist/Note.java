package com.example.todolist;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String describtion;

    private int priority;

    public Note(String title, String describtion, int priority) {
        this.title = title;
        this.describtion = describtion;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescribtion() {
        return describtion;
    }

    public int getPriority() {
        return priority;
    }
}
