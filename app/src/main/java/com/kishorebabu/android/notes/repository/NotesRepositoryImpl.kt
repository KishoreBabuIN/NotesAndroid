package com.kishorebabu.android.notes.repository

import com.kishorebabu.android.notes.persistence.NotesDatabase
import com.kishorebabu.android.notes.persistence.model.Note

class NotesRepositoryImpl(private val notesDatabase: NotesDatabase) : NotesRepository {
  override suspend fun getAllNotes(): List<Note> {
    return notesDatabase.noteDao()
        .getAllNotes()
  }

  override suspend fun addNote(note: Note): Long {
    return notesDatabase.noteDao()
        .addNote(note)
  }

  override suspend fun fetchNoteForId(noteId: Long): Note? {
    return notesDatabase.noteDao()
        .getNoteForId(noteId)
  }
}