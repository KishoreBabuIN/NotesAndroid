package com.kishorebabu.android.notes.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.usecase.FetchNoteByIdUseCase
import kotlinx.coroutines.launch

class NoteViewViewModel constructor(private val fetchNoteByIdUseCase: FetchNoteByIdUseCase) : ViewModel() {

  private val stateMutableLiveData: MutableLiveData<DisplayNoteFragmentState> = MutableLiveData()
  fun getStateLiveData(): LiveData<DisplayNoteFragmentState> = stateMutableLiveData

  fun getNote(id: Long) {
    stateMutableLiveData.value = DisplayNoteFragmentState.Loading
    viewModelScope.launch {
      fetchNoteByIdUseCase.perform(id)?.let {
        stateMutableLiveData.postValue(DisplayNoteFragmentState.Success(it))
      }
          ?: stateMutableLiveData.postValue(DisplayNoteFragmentState.Error(Exception("No note saved locally with given Id")))
    }

  }
}

sealed class DisplayNoteFragmentState {
  object Loading : DisplayNoteFragmentState()
  class Success(val note: Note) : DisplayNoteFragmentState()
  class Error(val exception: Exception) : DisplayNoteFragmentState()
}