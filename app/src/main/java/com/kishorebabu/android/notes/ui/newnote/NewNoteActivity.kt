package com.kishorebabu.android.notes.ui.newnote

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.kishorebabu.android.notes.R
import com.kishorebabu.android.notes.R.string
import com.kishorebabu.android.notes.databinding.ActivityNewNoteBinding
import com.kishorebabu.android.notes.ui.base.BaseActivity
import com.kishorebabu.android.notes.ui.newnote.NewNoteViewModel.AddNoteState
import com.kishorebabu.android.notes.ui.note.NoteViewActivity
import com.kishorebabu.android.notes.util.DialogFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewNoteActivity : BaseActivity<ActivityNewNoteBinding>() {
  override fun layoutRes() = R.layout.activity_new_note

  private val newNoteViewModel: NewNoteViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initUI()
  }

  private fun initUI() {
    binding.run {
      etTitle.addTextChangedListener(textWatcher)
      etContent.addTextChangedListener(textWatcher)
    }
    initListeners()
    binding.etTitle.requestFocus()
  }

  private fun initListeners() {
    newNoteViewModel.getStateLiveDate()
        .observe(this, Observer {
          it?.let { state ->
            when (state) {
              is AddNoteState.Loading -> {
                binding.progressCircular.visibility = View.VISIBLE
              }
              is AddNoteState.Successful -> {
                binding.progressCircular.visibility = View.GONE
                onNoteSaved(state.noteid)
              }
            }
          }
        })
    binding.btnSave.setOnClickListener {
      newNoteViewModel.saveNote(title = binding.etTitle.text.toString(), content = binding.etContent.text.toString())
    }
  }

  override fun onBackPressed() = if (!isUnsavedNote()) {
    super.onBackPressed()
  } else {
    DialogFactory.showConfirmDialog(
        this,
        getString(string.discard_note_title),
        getString(string.discard_note_body),
        getString(string.discard),
        getString(string.cancel),
        DialogInterface.OnClickListener { dialog, which -> finish() },
        DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() }
    )
  }

  private fun isUnsavedNote(): Boolean = binding.etContent.text?.isNotEmpty() == true || binding.etTitle.text?.isNotEmpty() == true

  private fun onNoteSaved(id: Long) {
    startActivity(Intent(this, NoteViewActivity::class.java).apply {
      this.putExtra(NoteViewActivity.NOTE_ID, id)
    })
    finish()
  }

  private val textWatcher = object : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
      validateEmptyTexts()
    }

    override fun beforeTextChanged(
      p0: CharSequence?,
      p1: Int,
      p2: Int,
      p3: Int
    ) = Unit

    override fun onTextChanged(
      p0: CharSequence?,
      p1: Int,
      p2: Int,
      p3: Int
    ) = Unit
  }

  private fun validateEmptyTexts() {
    binding.btnSave.isEnabled = when {
      binding.etContent.text.isNullOrEmpty() || binding.etTitle.text.isNullOrEmpty() -> false
      else -> true
    }
  }
}