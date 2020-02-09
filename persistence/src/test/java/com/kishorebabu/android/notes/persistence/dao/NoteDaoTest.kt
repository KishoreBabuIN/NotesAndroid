package com.kishorebabu.android.notes.persistence.dao

import android.os.Build
import com.kishorebabu.android.notes.persistence.util.NoteUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NoteDaoTest : BaseDaoTest() {

  private val fakeNoteData = NoteUtils.FakeNoteData

  private val noteDao: NoteDao by lazy {
    notesDatabase.noteDao()
  }

  @Test
  fun `get all notes`() {
    runBlocking {
      val listofIds = noteDao.addNotes(fakeNoteData.notes)
      val notes = noteDao.getAllNotes()

      assertEquals(true, notes.first().id == listofIds.first())
    }
  }

  @Test
  fun `get note by id`() {
    runBlocking {
      val listofIds = noteDao.addNotes(fakeNoteData.notes)
      val note = noteDao.getNoteForId(listofIds.first())
      assertTrue(note != null)
      assertTrue(note!!.id == listofIds.first())
    }
  }
}