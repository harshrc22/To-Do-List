package com.example.notesapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "USER")
data class Notesdata(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") val title: String ?,
    @ColumnInfo(name = "notes") val notes: String ?,
    @ColumnInfo(name = "date") val date: String ?

) :Serializable

