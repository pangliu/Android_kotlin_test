package infinitas.com.indochat.kotlindemo.repository

import android.util.Log
import infinitas.com.indochat.kotlindemo.data.StationArray
import infinitas.com.indochat.kotlindemo.helper.ApiSourceHelper
import kotlinx.coroutines.*

class StationModel {

    val API_DOMAIN = "http://test-bike.infinitas.tech"

    interface onLoadStationCallback {
        fun onLoadFailed(errorMsg: String)
        fun onLoadReady()
        fun onLoadSuccess(stationArray: StationArray)
    }

    fun loadStationData(onLoadStation: onLoadStationCallback) {
        //準備進入 IO 部分
        onLoadStation.onLoadReady()

        CoroutineScope(Dispatchers.IO).launch {
            var apiSourceHelper = ApiSourceHelper(API_DOMAIN)
            val resp = apiSourceHelper.getStation().await()

            // Switch to Android mainThread
            withContext(Dispatchers.Main) {
                if (resp.isSuccessful) {
                    val respObject: StationArray? = resp.body()
                    onLoadStation.onLoadSuccess(respObject!!)
                } else {
                    val errorBody = resp.errorBody().toString()
                    Log.d("msg", "StationModel: ${errorBody}")
                    onLoadStation.onLoadFailed(errorBody)
                }
            }
        }
    }

}