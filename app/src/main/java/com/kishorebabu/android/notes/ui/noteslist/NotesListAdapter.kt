package com.kishorebabu.android.notes.ui.noteslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kishorebabu.android.notes.databinding.ItemNoteBinding
import com.kishorebabu.android.notes.persistence.model.Note
import com.kishorebabu.android.notes.util.toReadableDate

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.NoteItemViewHolder>() {

  private val noteList: MutableList<Note> = mutableListOf()
  var onNoteClicked: ((id: Long) -> Unit)? = null

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): NoteItemViewHolder {
    val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return NoteItemViewHolder(binding)
  }

  override fun getItemCount() = noteList.count()

  override fun onBindViewHolder(
    holder: NoteItemViewHolder,
    position: Int
  ) {
    holder.bindData(noteList[position])
    holder.itemView.setOnClickListener {
      onNoteClicked?.invoke(noteList[position].id)
    }
  }

  fun updateNotes(notes: List<Note>) {
    val result: DiffUtil.DiffResult =
      DiffUtil.calculateDiff(NotesDiffUtil(notes, this.noteList))
    result.dispatchUpdatesTo(this)
    this.noteList.apply {
      clear()
      addAll(notes)
    }
  }

  class NoteItemViewHolder(private val binding: ItemNoteBinding) :
      RecyclerView.ViewHolder(binding.root) {
    fun bindData(note: Note) {
      binding.run {
        tvBody.text = note.body
        tvTitle.text = note.title
        tvDate.text = note.date.toReadableDate()
      }
    }
  }
}