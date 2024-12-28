package com.blinklab.notesapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,

    val noteTitle : String,
    val noteText: String,
    val priority: String,
    val date: String,

):Serializable


