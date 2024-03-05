package com.example.notesapp.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.repository.Noterepository

class Viewmodelfactory(private val repository:Noterepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Noteviewmodel::class.java)){
            return Noteviewmodel(repository) as T
        }
        throw IllegalAccessException("Unknow view model")
    }
}