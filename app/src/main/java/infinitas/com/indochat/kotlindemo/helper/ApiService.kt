package infinitas.com.indochat.kotlindemo.helper

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import infinitas.com.indochat.kotlindemo.data.StationArray
import infinitas.com.indochat.kotlindemo.data.VersionInfo
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface ApiService {

    companion object {
        private val gson by lazy { Gson() }
        private val API_DOMAIN = "http://test-bike.infinitas.tech"

        operator fun invoke(): ApiService {
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(ApplicationInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(API_DOMAIN)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }

    @POST("/api_v1/show_stations")
    fun getStation(@Body body: RequestBody): Call<StationArray>

    @POST("/api_v1/version")
    fun getVersion(@Body body: RequestBody): Call<VersionInfo>
}