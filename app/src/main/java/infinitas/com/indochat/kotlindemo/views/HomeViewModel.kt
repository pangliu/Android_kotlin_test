package infinitas.com.indochat.kotlindemo.views

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinitas.com.indochat.kotlindemo.data.StationInfo
import infinitas.com.indochat.kotlindemo.repository.StationModel

class HomeViewModel: AndroidViewModel {

    constructor(application: Application) : super(application) {

    }

    private val stationInfo: MutableLiveData<StationInfo> by lazy {
        MutableLiveData<StationInfo>().also {
            Log.d("msg", "homeViewModel stationInfo")
            loadStationInfo()
        }
    }

    // 呼叫 Model 取得 stationInfo
    private fun loadStationInfo() {
        var stationModel = StationModel()
        var station = stationModel.loadStation()

    }

    fun getStation(): LiveData<StationInfo> {
        return stationInfo
    }
}