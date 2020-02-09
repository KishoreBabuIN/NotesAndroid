package com.kishorebabu.android.notes.ui.newnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.usecase.NewNoteUseCase
import kotlinx.coroutines.launch

class NewNoteViewModel constructor(private val newNoteUseCase: NewNoteUseCase) : ViewModel() {

  private val stateLiveData: MutableLiveData<AddNoteState> = MutableLiveData()
  fun getStateLiveDate(): LiveData<AddNoteState> = stateLiveData

  fun saveNote(
    title: String,
    content: String
  ) {
    stateLiveData.value = AddNoteState.Loading
    viewModelScope.launch {
      val note = Note(title, content, System.currentTimeMillis())
      val noteId = newNoteUseCase.perform(note)
      stateLiveData.postValue(AddNoteState.Successful(noteId))
    }
  }

  sealed class AddNoteState {
    object Loading : AddNoteState()
    class Successful(val noteid: Long) : AddNoteState()
  }
}