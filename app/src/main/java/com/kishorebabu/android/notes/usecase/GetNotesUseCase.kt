package com.kishorebabu.android.notes.usecase

import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.repository.NotesRepository

class GetNotesUseCase constructor(private val notesRepository: NotesRepository) : BaseUseCase<List<Note>, Unit> {
  override suspend fun perform(): List<Note> {
    return notesRepository.getAllNotes()
  }
}