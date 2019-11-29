package com.example.seggro_ui;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {


    // unique key to identify each row...........

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String temperature;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }




    @ColumnInfo(name = "created_at")
//    @TypeConverters({TimestampConverter.class})
//    private Date createdAt;

//    @ColumnInfo(name = "modified_at")
//    @TypeConverters({TimestampConverter.class})
//    private Date modifiedAt;

//    private boolean encrypt;
//    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getModifiedAt() {
//        return modifiedAt;
//    }
//
//    public void setModifiedAt(Date modifiedAt) {
//        this.modifiedAt = modifiedAt;
//    }

//    public boolean isEncrypt() {
//        return encrypt;
//    }
//
//    public void setEncrypt(boolean encrypt) {
//        this.encrypt = encrypt;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}