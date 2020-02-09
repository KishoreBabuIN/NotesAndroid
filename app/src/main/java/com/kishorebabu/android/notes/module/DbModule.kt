package com.kishorebabu.android.notes.module

import com.kishorebabu.android.notes.persistence.NotesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module{
  single {
    NotesDatabase.getInstance(androidContext())
  }
}