package com.kishorebabu.android.notes.ui.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.usecase.GetNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListViewModel constructor(
  private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {
  private val notesListLiveData: MutableLiveData<List<Note>> = MutableLiveData()
  private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

  fun getNotesListLiveData(): LiveData<List<Note>> = notesListLiveData

  fun getAllNotes() {
    loadingLiveData.value = true
    viewModelScope.launch(Dispatchers.Default) {
      notesListLiveData.postValue(getNotesUseCase.perform())
      loadingLiveData.postValue(false)
    }
  }

  fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

}