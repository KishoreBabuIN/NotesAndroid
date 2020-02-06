package com.kishorebabu.android.notes.persistence.dao

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.kishorebabu.android.notes.persistence.NotesDatabase
import org.junit.After
import org.junit.Before

abstract class BaseDaoTest {
  lateinit var notesDatabase: NotesDatabase

  @Before
  fun setup(){
    val context = getApplicationContext() as Application
    notesDatabase =
      Room.inMemoryDatabaseBuilder(context, NotesDatabase::class.java)
          .allowMainThreadQueries()
          .build()
  }

  @After
  fun tearDown(){

  }
}