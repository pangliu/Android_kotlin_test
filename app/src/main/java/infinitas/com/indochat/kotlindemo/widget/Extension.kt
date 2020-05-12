package infinitas.com.indochat.kotlindemo.widget

import android.content.Context
import androidx.appcompat.app.AlertDialog
import infinitas.com.indochat.kotlindemo.R

var extensionProgress: AlertDialog? = null

fun runProgress(myContext: Context, loading: Boolean) {
    if (extensionProgress == null) {
        extensionProgress = AlertDialog.Builder(myContext, R.style.Base_ThemeOverlay_AppCompat_Dialog).setCancelable(true)
            .setView(R.layout.progress_dialog).create()
    }
    when (loading) {
        true -> extensionProgress!!.show()
        false -> {
            extensionProgress!!.dismiss()
            extensionProgress = null
        }
    }
}