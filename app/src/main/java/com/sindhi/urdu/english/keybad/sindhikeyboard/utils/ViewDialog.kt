package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class ViewDialog {
    fun showDialog(activity: Activity?, msg: String?) {
        val dialog = activity?.let { Dialog(it) }

        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(com.sindhi.urdu.english.keybad.R.layout.dialog)
            val text = dialog.findViewById(com.sindhi.urdu.english.keybad.R.id.tvexitdialog) as TextView
            text.text = msg
        }

        val btnno: Button = dialog?.findViewById(com.sindhi.urdu.english.keybad.R.id.btnno) as Button
        btnno.blockingClickListener { dialog.dismiss() }
        val btnyes: Button = dialog.findViewById(com.sindhi.urdu.english.keybad.R.id.btnyes) as Button

        btnyes.blockingClickListener {
            activity.finishAffinity()
            exitProcess(0)
        }

        dialog.show()
    }
}