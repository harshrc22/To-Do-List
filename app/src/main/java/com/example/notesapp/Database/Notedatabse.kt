package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notesapp.Data.DAOData
import com.example.notesapp.Data.Notesdata
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notesdata::class], version = 1,
    exportSchema = false)
abstract class Notedatabase :RoomDatabase() {
    abstract fun daodata():DAOData
    companion object{
@Volatile
        private  var Instance:Notedatabase ?=null
       @OptIn(InternalCoroutinesApi::class)
       fun getinstance(context:Context):Notedatabase{
           var instance= Instance
          if(instance==null){
              synchronized(this){

                  instance= Room.databaseBuilder(context.applicationContext,Notedatabase::class.java,"USER").fallbackToDestructiveMigration().build()
              }

              Instance=instance


          }
           return Instance!!
       }
    }



}