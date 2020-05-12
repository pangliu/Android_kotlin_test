package infinitas.com.indochat.kotlindemo.widget

import android.util.Log

class TestObj {

    interface intercaceObj {
        fun callback(param: String): String
    }

    companion object {
        private val TextContent = "TextContext"

        operator fun invoke(): String {
            Log.d("msg", "invoke: ")
            return TextContent
        }

        operator fun invoke(index: Int): String {
            Log.d("msg", "invoke: " + index)
            return TextContent
        }
    }

    fun sun(index: Int, action: (param:String)->String): String {
        var y = index+1
        var s = action("erer")
        Log.d("msg", "sun: $s + $y")
        return s
    }

}