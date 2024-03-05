package com.example.notesapp.repository

import com.example.notesapp.Data.DAOData
import com.example.notesapp.Data.Notesdata

class Noterepository(private val dao:DAOData) {
    val notedata=dao.getdata()
    suspend fun insert(note:Notesdata){
        dao.insert(note)
    }
    suspend fun delete(note: Notesdata){
        dao.delete(note)
    }
    suspend fun update(note:Notesdata){
        dao.update(note)
    }
}