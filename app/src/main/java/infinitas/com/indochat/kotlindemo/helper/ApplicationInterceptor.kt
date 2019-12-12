package infinitas.com.indochat.kotlindemo.helper

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class ApplicationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var response: Response = chain.proceed(request)
        var respStr = response.body()?.string()
        Log.d("msg", "resp: ${respStr}")

        return response
            .newBuilder()
            .body(ResponseBody.create(response.body()?.contentType(), respStr))
            .build()
    }
}