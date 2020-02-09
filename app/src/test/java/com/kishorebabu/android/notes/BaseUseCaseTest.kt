package com.kishorebabu.android.notes

import androidx.room.Room
import com.kishorebabu.android.notes.module.useCaseModule
import com.kishorebabu.android.notes.persistence.NotesDatabase
import com.kishorebabu.android.notes.repository.NotesRepository
import com.kishorebabu.android.notes.repository.NotesRepositoryImpl
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RuntimeEnvironment

open class BaseUseCaseTest : KoinTest {
  protected lateinit var notesDatabase: NotesDatabase

  @Before
  fun setup() {
    notesDatabase = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application.baseContext, NotesDatabase::class.java)
        .allowMainThreadQueries()
        .build()

    startKoin {
      modules(
          listOf(
              module {
                single<NotesRepository> {
                  NotesRepositoryImpl(notesDatabase)
                }
              }, useCaseModule
          )
      )
    }
  }

  @After
  fun teardown() {
    stopKoin()
  }
}