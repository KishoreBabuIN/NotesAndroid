package com.kishorebabu.android.notes.ui.noteslist

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kishorebabu.android.notes.R
import com.kishorebabu.android.notes.databinding.ActivityNotesListBinding
import com.kishorebabu.android.notes.ui.base.BaseActivity
import com.kishorebabu.android.notes.ui.newnote.NewNoteActivity
import com.kishorebabu.android.notes.ui.note.NoteViewActivity
import com.kishorebabu.android.notes.util.setVerticalDividerDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListActivity : BaseActivity<ActivityNotesListBinding>() {

  override fun layoutRes() = R.layout.activity_notes_list

  private val notesListViewModel: NotesListViewModel by viewModel()

  private val notesAdapter: NotesListAdapter by lazy { NotesListAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initUI()
    initListeners()
  }

  override fun onResume() {
    super.onResume()
    notesListViewModel.getAllNotes()
  }

  private fun initUI() {
    binding.toolbar.title = getString(R.string.app_name)
    binding.rvNotes.apply {
      layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
      setVerticalDividerDecoration(R.drawable.divider_drawable)
      adapter = notesAdapter
    }
  }

  private fun initListeners() {
    notesListViewModel.getNotesListLiveData()
        .observe(this, Observer { notes ->
          binding.isEmptyList = notes.isEmpty()
          notesAdapter.updateNotes(notes)
        })
    binding.fabNewNote.setOnClickListener {
      startActivity(Intent(this@NotesListActivity, NewNoteActivity::class.java))
    }
    notesAdapter.onNoteClicked = { noteId ->
      startActivity(Intent(this, NoteViewActivity::class.java).apply {
        this.putExtra(NoteViewActivity.NOTE_ID, noteId)
      })
    }
  }

}