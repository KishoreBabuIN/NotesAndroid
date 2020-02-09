package com.kishorebabu.android.notes

import android.os.Build
import androidx.room.Room
import com.kishorebabu.android.notes.module.useCaseModule
import com.kishorebabu.android.notes.persistence.NotesDatabase
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.repository.NotesRepository
import com.kishorebabu.android.notes.repository.NotesRepositoryImpl
import com.kishorebabu.android.notes.usecase.NewNoteUseCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NewNoteUseCaseTest : KoinTest {

  private val newNoteUseCase: NewNoteUseCase by inject()
  private lateinit var database: NotesDatabase

  @Before
  fun setup() {
    database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application.baseContext, NotesDatabase::class.java)
        .allowMainThreadQueries()
        .build()

    startKoin {
      modules(
          listOf(
              module {
                single<NotesRepository> {
                  NotesRepositoryImpl(database)
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

  @Test
  fun `note with non empty title and body is saved successfully`() {
    val notesApp = Note("Random Title", "Random Body", System.currentTimeMillis())
    runBlocking {
      newNoteUseCase.perform(notesApp)
      val notes = database.noteDao()
          .getAllNotes()
      assertTrue(notes.isNotEmpty())
    }
  }

  @Test(expected = IllegalArgumentException::class)
  fun `note with empty title throws an exception`() {
    val notesApp = Note("", "random body", System.currentTimeMillis())
    runBlocking {
      newNoteUseCase.perform(notesApp)
    }
  }

  @Test(expected = IllegalArgumentException::class)
  fun `note with empty body throws an exception`() {
    val notesApp = Note("random title", "", System.currentTimeMillis())
    runBlocking {
      newNoteUseCase.perform(notesApp)
    }
  }

}