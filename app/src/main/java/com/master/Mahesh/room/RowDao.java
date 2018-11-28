package com.master.Mahesh.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RowDao {

    @Insert
    void insert(RowEntity repo);

    @Query("SELECT * FROM `row`")
    List<RowEntity> getAllRow();

    @Query("SELECT id,row1,row2,row3,row4 FROM `row`")
    public List<RowEntity> getAllUsers();
}
