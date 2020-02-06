package com.kishorebabu.android.notes.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Note.NOTE)
data class Note(
  val title: String,
  val body: String,
  val date: Long
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0

  companion object {
    const val NOTE = "table_note"
  }
}