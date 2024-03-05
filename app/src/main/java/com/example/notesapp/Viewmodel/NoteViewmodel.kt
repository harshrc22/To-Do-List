package com.example.notesapp.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Data.Notesdata
import com.example.notesapp.repository.Noterepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Noteviewmodel(private val repository:Noterepository):ViewModel() {
   val notesdata=repository.notedata
    fun insert(note:Notesdata){
      viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
      }

    }
    fun updatenote(note:Notesdata){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(note)
        }

    }
    fun delete(note:Notesdata){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(note)
        }

    }
}