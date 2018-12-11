package com.master.Mahesh.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RowDao {

    @Insert
    void insert(RowEntity repo);



    @Query("SELECT id,row1,row2,row3,row4,tab_position FROM `row`")
    public List<RowEntity> getAllRow();

    @Query("UPDATE `row` SET row1 = :row1,row1 = :row1, row2 = :row2,row3 = :row3,row4 = :row4 WHERE tab_position = :tab_position and id =:id")
    int updateRow(String id,String tab_position, String row1,String row2,String row3,String row4);

    @Query("SELECT id,row1,row2,row3,row4,tab_position FROM `row` where tab_position =:tab_position")
    public List<RowEntity> getRowByPositon(String tab_position);
}
