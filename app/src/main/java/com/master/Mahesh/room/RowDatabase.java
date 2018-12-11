package com.master.Mahesh.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = { RowEntity.class }, version = 2)
public abstract class RowDatabase extends RoomDatabase {

    private static final String DB_NAME = "rowDatabase.db";
    private static volatile RowDatabase instance;

    public static synchronized RowDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RowDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RowDatabase.class,
                DB_NAME).build();
    }

    public abstract RowDao getRowDao();
}
