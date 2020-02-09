package com.kishorebabu.android.notes

import android.os.Build
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.usecase.NewNoteUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NewNoteUseCaseTest : BaseUseCaseTest() {

  private val newNoteUseCase: NewNoteUseCase by inject()

  @Test
  fun `note with non empty title and body is saved successfully`() {
    val notesApp = Note("Random Title", "Random Body", System.currentTimeMillis())
    runBlocking {
      newNoteUseCase.perform(notesApp)
      val notes = notesDatabase.noteDao()
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