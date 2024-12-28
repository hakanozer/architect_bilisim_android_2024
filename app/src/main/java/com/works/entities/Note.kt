package com.works.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nid" )
    val nid: Int = 0,

    val title: String,
    val content: String,

)
