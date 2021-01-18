package com.karan.musicwiki.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.karan.musicwiki.database.converter.TypeConverter;
import com.karan.musicwiki.database.entity.Genre;


@Database(entities = {Genre.class}, version = 9, exportSchema = false)
@TypeConverters({TypeConverter.class})
public abstract class MyDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile MyDatabase INSTANCE;

    //  ---Dao---



}
