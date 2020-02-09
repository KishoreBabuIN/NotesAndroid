package com.kishorebabu.android.notes.util

import java.text.SimpleDateFormat
import java.util.Calendar

fun Long.toReadableDate(format: String = "EEE, MMM/dd/yyyy"): String {
  val calendar = Calendar.getInstance()
  calendar.timeInMillis = this
  val simpleDateFormat = SimpleDateFormat(format)
  return simpleDateFormat.format(calendar.time)
}