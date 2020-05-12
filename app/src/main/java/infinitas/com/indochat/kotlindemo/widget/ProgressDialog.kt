package infinitas.com.indochat.kotlindemo.widget

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import infinitas.com.indochat.kotlindemo.R

class ProgressDialog(context: Context) {

    var context: Context
    var builder: AlertDialog.Builder
    var dialogView: View
    //    val tvDialogMsg = dialogView.findViewById<TextView>(R.id.tv_progress_msg)
    var tvDialogMsg: TextView
    val dialog: AlertDialog

    init {
        this.context = context
        builder = AlertDialog.Builder(context)
        dialogView = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
        tvDialogMsg = dialogView.findViewById<TextView>(R.id.tv_progress_msg)
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