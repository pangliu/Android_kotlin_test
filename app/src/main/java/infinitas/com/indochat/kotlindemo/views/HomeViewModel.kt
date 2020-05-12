package infinitas.com.indochat.kotlindemo.views

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinitas.com.indochat.kotlindemo.data.StationArray
import infinitas.com.indochat.kotlindemo.helper.ApiService
import infinitas.com.indochat.kotlindemo.repository.DataImpl
import infinitas.com.indochat.kotlindemo.repository.StationModel
import infinitas.com.indochat.kotlindemo.widget.ProgressDialog

class HomeViewModel(private val dataImpl: DataImpl): ViewModel() {

//    lateinit var progressDialog: ProgressDialog
//    private lateinit var context: Context

//    constructor(application: Application) : super(application) {
//        context = application.applicationContext
//        progressDialog = ProgressDialog(context)
//    }

//    private val stationInfo: MutableLiveData<StationArray> by lazy {
//        MutableLiveData<StationArray>().also {
//            Log.d("msg", "homeViewModel stationInfo")
//            loadStationInfo()
//        }
//    }

    // 呼叫 Model 取得 stationInfo
//    private fun loadStationInfo() {
//        var stationModel = StationModel()
//        stationModel.loadStationData(object: StationModel.onLoadStationCallback {
//            override fun onLoadReady() {
//                Log.d("msg", "onLoadReady")
////                progressDialog.setDialogMsg("loading....")
////                progressDialog.showDialog()
//            }
//            override fun onLoadSuccess(stationArray: StationArray) {
//                Log.d("msg", "onLoadSuccess: ${stationArray.status}")
//                progressDialog.dismissDialog()
//            }
//
//            override fun onLoadFailed(errorMsg: String) {
//                Log.d("msg", "onLoadFailed: errorMsg:${errorMsg}")
//                progressDialog.dismissDialog()
//            }
//        })
//    }

//    fun getStation(): LiveData<StationArray> {
//        return stationInfo
//    }

    suspend fun testStation(loading: Boolean, param: String) = dataImpl.getStation(loading, param)

}