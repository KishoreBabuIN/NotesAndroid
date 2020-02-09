package com.kishorebabu.android.notes.module

import com.kishorebabu.android.notes.persistence.NotesDatabase
import com.kishorebabu.android.notes.repository.NotesRepository
import com.kishorebabu.android.notes.repository.NotesRepositoryImpl
import com.kishorebabu.android.notes.ui.newnote.NewNoteViewModel
import com.kishorebabu.android.notes.ui.note.NoteViewViewModel
import com.kishorebabu.android.notes.ui.noteslist.NotesListViewModel
import com.kishorebabu.android.notes.usecase.FetchNoteByIdUseCase
import com.kishorebabu.android.notes.usecase.GetNotesUseCase
import com.kishorebabu.android.notes.usecase.NewNoteUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
  single {
    NotesDatabase.getInstance(androidContext())
  }
}

val viewModelModule = module {
  factory { NewNoteViewModel(get()) }
  factory { NotesListViewModel(get()) }
  factory { NoteViewViewModel(get()) }
}

val useCaseModule = module {
  factory { NewNoteUseCase(get()) }
  factory { GetNotesUseCase(get()) }
  factory { FetchNoteByIdUseCase(get()) }
}

val repositoryModule = module {
  single<NotesRepository> {
    NotesRepositoryImpl(get())
  }
}