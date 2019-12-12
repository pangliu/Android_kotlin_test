package infinitas.com.indochat.kotlindemo.helper

import android.util.Log
import infinitas.com.indochat.kotlindemo.data.StationArray
import infinitas.com.indochat.kotlindemo.data.VersionInfo
import io.reactivex.Flowable
import kotlinx.coroutines.Deferred
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

class ApiSourceHelper {

    var apiService:ApiService
    val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!

    constructor(domainUrl: String) {
        var retrofitHelper: RetrofitHelper = RetrofitHelper()
        this.apiService = retrofitHelper.getRetrofit(domainUrl).create(ApiService::class.java)
    }

    interface ApiService {

        @POST("/api_v1/version")
        fun postVersion(@Body body: RequestBody): Deferred<Response<VersionInfo>>

        @POST("/api_v1/show_stations")
        fun postStation(@Body body: RequestBody): Deferred<Response<StationArray>>
    }

    fun getVersion(device: String): Deferred<Response<VersionInfo>> {
        var json: JSONObject = JSONObject()
        try {
            json.put("device", device)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        var body: RequestBody = RequestBody.create(MEDIA_TYPE, json.toString())
        return apiService.postVersion(body)
    }

    fun getStation(): Deferred<Response<StationArray>> {
        var json: JSONObject = JSONObject()
        var body: RequestBody = RequestBody.create(MEDIA_TYPE, json.toString())
        return  apiService.postStation(body)
    }
}