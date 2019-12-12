package infinitas.com.indochat.kotlindemo.widget

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.widget.TextView
import infinitas.com.indochat.kotlindemo.R

class ProgressDialog(activity: Activity) {

//    val activity = context.applicationContext

    val builder = AlertDialog.Builder(activity)
    val dialogView = activity.layoutInflater.inflate(R.layout.progress_dialog, null)
    val tvDialogMsg = dialogView.findViewById<TextView>(R.id.tv_progress_msg)
    val dialog: AlertDialog

    init {
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun setDialogMsg(msg:String) {
        tvDialogMsg.text = msg
    }

    fun showDialog() {
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}