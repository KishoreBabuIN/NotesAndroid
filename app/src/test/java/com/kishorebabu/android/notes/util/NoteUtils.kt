package com.kishorebabu.android.notes.util

import com.kishorebabu.android.notes.persistence.model.Note

object NoteUtils {
  object FakeNoteData {
    val notes = mutableListOf<Note>().apply {
      for (i in 1..5) {
        add(
            Note(
                title = "Note Title #$i",
                body = "Lorem Ipsum Body #$i",
                date = System.currentTimeMillis()
            )
        )
      }
    }
  }
}