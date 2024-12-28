package com.works.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.works.dao.NoteDao
import com.works.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}