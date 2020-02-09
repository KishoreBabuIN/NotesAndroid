package com.kishorebabu.android.notes.util

import android.content.Context
import android.text.format.DateUtils
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun Long.toReadableDate(context: Context): String {
  return DateUtils
      .getRelativeDateTimeString(context, this, DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL)
      .toString()
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