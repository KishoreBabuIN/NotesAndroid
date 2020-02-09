package com.kishorebabu.android.notes.usecase

import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.repository.NotesRepository

class NewNoteUseCase constructor(private val repository: NotesRepository) : BaseUseCase<Long, Note> {
  /**
   * Save a new note
   * throws @exception if empty data passes in any field
   */
  override suspend fun perform(params: Note): Long {
    validateNote(params)
    return repository.addNote(params)
  }

  private fun validateNote(note: Note) {
    note.run {
      when {
        title.isNullOrEmpty() -> throw IllegalArgumentException("Title of $note cannot be empty")
        body.isNullOrEmpty() -> throw IllegalArgumentException("Content of $note cannot be empty")
        else -> {
          //do nothing
        }
      }
    }
  }
}