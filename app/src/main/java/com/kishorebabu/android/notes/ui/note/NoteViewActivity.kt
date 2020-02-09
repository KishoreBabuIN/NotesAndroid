package com.kishorebabu.android.notes.ui.note

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kishorebabu.android.notes.R
import com.kishorebabu.android.notes.databinding.ActivityNoteBinding
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.ui.base.BaseActivity
import com.kishorebabu.android.notes.ui.note.DisplayNoteFragmentState.Error
import com.kishorebabu.android.notes.ui.note.DisplayNoteFragmentState.Loading
import com.kishorebabu.android.notes.ui.note.DisplayNoteFragmentState.Success
import com.kishorebabu.android.notes.util.toReadableDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteViewActivity : BaseActivity<ActivityNoteBinding>() {
  companion object {
    const val NOTE_ID = "extra_note_id"
  }

  override fun layoutRes() = R.layout.activity_note

  private val noteViewMode: NoteViewViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initListeners()
    intent?.let { intent ->
      noteViewMode.getNote(intent.getLongExtra(NOTE_ID, -1))
    }
  }

  private fun initListeners() {
    noteViewMode.getStateLiveData()
        .observe(this, Observer {
          it.let { state ->
            when (state) {
              is Loading -> {
                binding.progressCircular.visibility = View.VISIBLE
              }
              is Success -> {
                binding.progressCircular.visibility = View.GONE
                loadData(state.note)
              }
              is Error -> {
                binding.progressCircular.visibility = View.GONE
                Toast.makeText(
                    this@NoteViewActivity,
                    state.exception.localizedMessage?.toString(),
                    Toast.LENGTH_LONG
                )
              }
            }
          }
        })
  }

  private fun loadData(note: Note) {
    binding.run {
      tvDate.text = getString(R.string.template_created_at, note.date.toReadableDate(this.root.context))
      tvTitle.text = note.title
      tvContent.text = note.body
    }
  }
}