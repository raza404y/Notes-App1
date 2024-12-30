package com.blinklab.notesapp.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blinklab.notesapp.database.dao.NoteDAO
import com.blinklab.notesapp.database.entities.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDAO

     companion object{
        private var instance  : NoteDatabase? = null

        fun createDatabase(context: Context):NoteDatabase{
            instance = Room.databaseBuilder(
                context.applicationContext, NoteDatabase::class.java, "note_db"
            )
                .allowMainThreadQueries().build()
            return instance!!
        }


    }
}