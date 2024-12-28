package com.works.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.works.entities.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note) : Long // return id

    @Delete
    fun delete(note: Note) : Int // return deleted row count

    @Update
    fun update(note: Note) : Int // return updated row count

    @Query("SELECT * FROM note")
    fun getAll() : List<Note>

    @Query("SELECT * FROM note WHERE nid = :nid")
    fun getById(nid: Int) : Note

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun getByTitle(title: String) : List<Note>

}