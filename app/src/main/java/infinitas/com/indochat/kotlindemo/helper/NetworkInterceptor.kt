package infinitas.com.indochat.kotlindemo.helper

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

public class NetworkInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var t1 = System.nanoTime()
        var response: Response = chain.proceed(request)
        var t2 = System.nanoTime()

        Log.d("msg", "domain url: " + request.url())
        return response
    }
}