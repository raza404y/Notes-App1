package com.blinklab.notesapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.blinklab.notesapp.database.entities.Note


@Dao
interface NoteDAO {


    @Insert
    fun insertNote(note:Note)

    @Update
    fun updateNote(notes:Note)

    @Delete
    fun deleteNote(note:Note)

    @Query("select * from Note order by id desc")
    fun getAllNotes():LiveData<List<Note>>

    @Query("select * from Note where priority=:priority order by id desc")
    fun getPriorityNotes(priority:String):LiveData<List<Note>>

    @Query("select * from Note where date=:date order by id desc")
    fun getNotesByDate(date:String):LiveData<List<Note>>

    @Query("select * from note where noteTitle like '%'|| :query ||'%' order by id desc")
    fun getNotesBySearch(query:String):List<Note>

}