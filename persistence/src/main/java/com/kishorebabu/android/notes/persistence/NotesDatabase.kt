package com.kishorebabu.android.notes.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kishorebabu.android.notes.persistence.dao.NoteDao
import com.kishorebabu.android.notes.persistence.model.Note

@Database(
    entities = [Note::class],
    version = NotesDatabase.VERSION
)
abstract class NotesDatabase : RoomDatabase() {
  companion object {
    const val VERSION = 1
    @JvmStatic
    fun getInstance(
      context: Context,
      dbName: String = "notes_database"
    ): NotesDatabase {
      return Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, dbName)
          .build()
    }
  }

  abstract fun noteDao(): NoteDao
}