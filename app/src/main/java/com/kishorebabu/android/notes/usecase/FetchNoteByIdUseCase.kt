package com.kishorebabu.android.notes.usecase

import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.repository.NotesRepository

class FetchNoteByIdUseCase constructor(private val repository: NotesRepository) : BaseUseCase<Note?, Long> {
  override suspend fun perform(params: Long): Note? {
    return repository.fetchNoteForId(params)
  }
}