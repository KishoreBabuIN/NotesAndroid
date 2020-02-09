package com.kishorebabu.android.notes

import android.os.Build
import com.kishorebabu.android.notes.usecase.GetNotesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class GetNotesUseCaseTest : BaseUseCaseTest() {

  private val getNotesUseCase: GetNotesUseCase by inject()

  @Test
  fun `can fetch all existing notes`() {
    val sizeOfItemsInserted = `add fake notes in database`()
    runBlocking {
      val notes = getNotesUseCase.perform()
      assertTrue(notes.size == sizeOfItemsInserted)
    }
  }
}