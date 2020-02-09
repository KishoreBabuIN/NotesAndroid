package com.kishorebabu.android.notes.util

import android.app.Activity
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AlertDialog

object DialogFactory {

  /**
   * Show Confirm Dialog
   *
   * @param context - Activity Context
   * @param title - Dialog title
   * @param message - Dialog Message
   * @param positiveBtnText - Used to set Positive Button for Dialog
   * @param negativeBtnText - Used to set Negative Button for Dialog
   * @param positiveClickListener - [android.content.DialogInterface.OnClickListener] for Positive Action
   * @param negativeClickListener - [android.content.DialogInterface.OnClickListener] for Negative Action(default is null-dismiss dialog)
   */
  @JvmStatic
  fun showConfirmDialog(
    activity: Activity,
    title: String,
    message: String?,
    positiveBtnText: String,
    negativeBtnText: String?,
    positiveClickListener: OnClickListener,
    negativeClickListener: OnClickListener? = null,
    cancelOnTouchOutside: Boolean = true
  ) {

    activity.runOnUiThread {
      val dialog = AlertDialog.Builder(activity)
          .setTitle(title)
          .setMessage(message)
          .setPositiveButton(positiveBtnText, positiveClickListener)
          .setNegativeButton(negativeBtnText, negativeClickListener)
          .create()

      dialog.setCanceledOnTouchOutside(cancelOnTouchOutside)
      dialog.show()
    }
  }
}