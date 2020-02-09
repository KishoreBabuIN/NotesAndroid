package com.kishorebabu.android.notes.module

import com.kishorebabu.android.notes.repository.NotesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
  single {
    NotesRepositoryImpl(get())
  }
}