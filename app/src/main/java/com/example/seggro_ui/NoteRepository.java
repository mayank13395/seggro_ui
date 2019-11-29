package com.example.seggro_ui;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class NoteRepository {

    private String DB_NAME = "db_image_weather";

    private NoteDatabase noteDatabase;
    public NoteRepository(Context context) {
        noteDatabase = Room.databaseBuilder(context, NoteDatabase.class, DB_NAME).build();
    }

//    public void insertTask(String title,
//                           String description, String temperature) {
//
//        insertTask(title, description,temperature);
//    }

    public void insertTask(String title,

                           String description, String temperature,String imagePath) {
        Log.i("In ", "I am 54555");

        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note. setTemperature(temperature);
        note.setImagePath(imagePath);
//        note.setCreatedAt(AppUtils.getCurrentDateTime());
//        note.setModifiedAt(AppUtils.getCurrentDateTime());
//        note.setEncrypt(encrypt);


//        if(encrypt) {
//            note.setPassword(AppUtils.generateHash(password));
//        } else note.setPassword(null);
        Log.i("In ", "I am 11111111");

        insertTask(note);
        Log.i("In ", "I am 000000000");

    }

    public void insertTask(final Note note) {
        Log.i("In ", "I am coming");

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().insertTask(note);
                System.out.println("Data written successfully.");
                Log.i("SavedData", "I am Mayank");
                return null;
            }
        }.execute();
        Log.i("SavedData", "I am Mayankkkkkkkkk");
    }

//    public void updateTask(final Note note) {
//        note.setModifiedAt(AppUtils.getCurrentDateTime());
//
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                noteDatabase.daoAccess().updateTask(note);
//                return null;
//            }
//        }.execute();
//    }

    public void deleteTask(final int id) {
        final LiveData<Note> task = getTask(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    noteDatabase.daoAccess().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteTask(final Note note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().deleteTask(note);
                return null;
            }
        }.execute();
    }

    public LiveData<Note> getTask(int id) {
        return noteDatabase.daoAccess().getTask(id);
    }

    public LiveData<List<Note>> getTasks() {
        return noteDatabase.daoAccess().fetchAllTasks();
    }
}
