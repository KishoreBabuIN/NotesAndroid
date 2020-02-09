package com.kishorebabu.android.notes.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kishorebabu.android.notes.persistence.model.Note

@Dao
interface NoteDao {
  @Query("SELECT * FROM ${Note.NOTE}")
  suspend fun getAllNotes(): List<Note>

  @Query("SELECT * FROM ${Note.NOTE} WHERE id = :noteId")
  suspend fun getNoteForId(noteId: Long): Note?

  /**
   * Add a Note.
   *
   * @param note
   * @return id of saved note
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addNote(note: Note): Long

  /**
   * Add a list of Notes
   *
   * @param notes
   * @return List of note Ids saved.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addNotes(notes: List<Note>): List<Long>
}
