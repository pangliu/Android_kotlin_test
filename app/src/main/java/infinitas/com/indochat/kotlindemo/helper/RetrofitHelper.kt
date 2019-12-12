package infinitas.com.indochat.kotlindemo.helper

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {

    var mOkHttpClient: OkHttpClient? = null

    fun getRetrofit(domain: String):Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl(domain)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClinet())
            .build()
        return retrofit
    }


    fun getOkHttpClinet(): OkHttpClient? {
        if(null == mOkHttpClient) {
            mOkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(NetworkInterceptor())
                .addInterceptor(ApplicationInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        }
        return mOkHttpClient
    }

}