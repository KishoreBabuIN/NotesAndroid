package com.kishorebabu.android.notes.repository

import com.kishorebabu.android.notes.persistence.model.Note

interface NotesRepository {
  suspend fun getAllNotes(): List<Note>
  suspend fun addNote(note: Note): Long
  suspend fun fetchNoteForId(noteId: Long): Note?
}