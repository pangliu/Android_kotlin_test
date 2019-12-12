package infinitas.com.indochat.kotlindemo.repository

import android.util.Log
import infinitas.com.indochat.kotlindemo.helper.ApiSourceHelper
import kotlinx.coroutines.*

class StationModel {

    val API_DOMAIN = "http://test-bike.infinitas.tech"

    fun loadStation() {
        Log.d("msg", "StationModel loadStation")
        CoroutineScope(Dispatchers.IO).launch {
            var apiSourceHelper = ApiSourceHelper(API_DOMAIN)
            val resp = apiSourceHelper.getStation().await()
            Log.d("msg", "resp isSuccessful: ${resp.isSuccessful}")
            // Switch to Android mainThread
            withContext(Dispatchers.Main) {
                if(resp.isSuccessful) {
                    val respObject = resp.body()

                } else {
                    val errorBody = resp.errorBody().toString()
                    Log.d("msg", "StationModel: ${errorBody}")
                }
            }

        }
    }
}