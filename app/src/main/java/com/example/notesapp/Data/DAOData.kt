package com.example.notesapp.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAOData {
    @Insert
    suspend fun insert(notesdata: Notesdata)
    @Delete
    suspend fun delete(notesdata: Notesdata)
    @Update
    suspend fun update(notesdata: Notesdata)
    @Query("select * from USER order by id ASC")
    fun getdata():LiveData<List<Notesdata>>
}