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
}
