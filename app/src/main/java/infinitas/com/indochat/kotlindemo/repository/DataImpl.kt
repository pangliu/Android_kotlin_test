package infinitas.com.indochat.kotlindemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import infinitas.com.indochat.kotlindemo.data.BaseInfo
import infinitas.com.indochat.kotlindemo.data.StationArray
import infinitas.com.indochat.kotlindemo.data.VersionInfo
import infinitas.com.indochat.kotlindemo.helper.ApiService
import infinitas.com.indochat.kotlindemo.widget.runProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


interface NetworkData {
    suspend fun getStation(loading: Boolean, param: String): LiveData<StationArray>
    suspend fun getVersion(loading: Boolean, param: String): LiveData<VersionInfo>
}

class DataImpl(private val apiService: ApiService, private val context: Context): NetworkData {

    companion object {
        val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!
    }

    suspend override fun getStation(loading: Boolean, param: String): LiveData<StationArray> =
        handleRequest(loading, "station") {
            Log.d("msg", "NetworkData getStation")
            var json: JSONObject = JSONObject()
            var body: RequestBody = RequestBody.create(MEDIA_TYPE, json.toString())
            apiService.getStation(body).execute()
        }

    suspend override fun getVersion(loading: Boolean, param: String): LiveData<VersionInfo> =
        handleRequest(loading, "version") {
            var json: JSONObject = JSONObject()
            try {
                json.put("device", param)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            var body: RequestBody = RequestBody.create(MEDIA_TYPE, json.toString())
            apiService.getVersion(body).execute()
        }

    private suspend fun <T: BaseInfo> handleRequest(loading: Boolean, tag: String, action: () -> Response<T>): LiveData<T> {
        Log.d("msg", "NetworkData handleRequest")
        if(loading) {
            runProgress(context, loading)
        }
        val liveData = MutableLiveData<T>()
        withContext(Dispatchers.IO) {
            try {
                Log.d("msg", "NetworkData withContext")
                val respose = action()
//                Log.d("msg", "response: " + respose.body().toString())
                if (respose.isSuccessful) {
                    val body = respose.body()
                    if (body != null) {
                        liveData.postValue(body)
                    } else {
                        //TODO
                    }
                } else {
                    // TODO
                }
            } catch (e: Exception) {
                Log.d("msg", "api error: ${e.toString()}")
            }
        }
        runProgress(context, false)
        return liveData
    }
}
