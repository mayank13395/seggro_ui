package com.example.seggro_ui;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import androidx.room.Dao;


@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
