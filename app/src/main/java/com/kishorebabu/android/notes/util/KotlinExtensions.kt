package com.kishorebabu.android.notes.util

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar

fun Long.toReadableDate(format: String = "EEE, MMM dd, yyyy"): String {
  val calendar = Calendar.getInstance()
  calendar.timeInMillis = this
  val simpleDateFormat = SimpleDateFormat(format)
  return simpleDateFormat.format(calendar.time)
}

fun RecyclerView.setVerticalDividerDecoration(@DrawableRes drawableRes: Int) {
  val divider = DividerItemDecoration(
      this.context,
      DividerItemDecoration.VERTICAL
  )
  val drawable = ContextCompat.getDrawable(
      this.context,
      drawableRes
  )
  drawable?.let {
    divider.setDrawable(it)
    addItemDecoration(divider)
  }
}