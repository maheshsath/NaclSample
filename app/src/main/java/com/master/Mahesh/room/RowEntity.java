package com.master.Mahesh.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "row")
public class RowEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int id;
    private String row1;
    private   String row2;
    private  String row3;
    private  String row4;


    public RowEntity(String row1,String row2, String row3, String row4) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.row4 = row4;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getRow1() {
        return row1;
    }

    public void setRow1(String row1) {
        this.row1 = row1;
    }

    public String getRow2() {
        return row2;
    }

    public void setRow2(String row2) {
        this.row2 = row2;
    }

    public String getRow3() {
        return row3;
    }

    public void setRow3(String row3) {
        this.row3 = row3;
    }

    public String getRow4() {
        return row4;
    }

    public void setRow4(String row4) {
        this.row4 = row4;
    }
}
