package com.kishorebabu.android.notes

import android.os.Build
import com.kishorebabu.android.notes.usecase.FetchNoteByIdUseCase
import com.kishorebabu.android.notes.util.NoteUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class FetchNoteByIdUseCaseTest : BaseUseCaseTest() {

  private val fetchNoteByIdUseCase: FetchNoteByIdUseCase by inject()
  lateinit var noteIds: List<Long>

  @Test
  fun `can fetch note with valid note id`() {
    `add dummy notes in database`()
    runBlocking {
      val note = fetchNoteByIdUseCase.perform(noteIds.first())
      Assert.assertEquals(note?.id, noteIds.first())
    }
  }

  @Test
  fun `cannot fetch note with invalid note id`() {
    `add dummy notes in database`()
    runBlocking {
      val note = fetchNoteByIdUseCase.perform(0)
      Assert.assertNull(note)
    }
  }

  private fun `add dummy notes in database`() {
    runBlocking {
      val notes = NoteUtils.FakeNoteData.notes
      noteIds = notesDatabase.noteDao()
          .addNotes(notes)
    }
  }
}