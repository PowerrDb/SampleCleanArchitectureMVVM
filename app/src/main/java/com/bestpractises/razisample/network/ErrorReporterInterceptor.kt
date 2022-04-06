package com.bestpractises.razisample.network

import android.util.Log
import com.bestpractises.razisample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.nio.charset.Charset


class ErrorReporterInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code !in 200..300) {
            try {
                val responseBody: ResponseBody = response.body!!
                val source = responseBody.source()
                source.request(Long.MAX_VALUE)
                val buffer: Buffer = source.buffer()
                val rawJson: String =
                    buffer.clone().readString(Charset.forName("UTF-8"))
                if (BuildConfig.DEBUG)
                Log.e("____ApiError", "RequestBody : "+response.request.body.toString()+"\n"+"Response ---> "+response.request.method +" - "+ response.code + " - "+response.request.url+"\n"+rawJson )
            } catch (e: Exception) {
                if (BuildConfig.DEBUG)
                Log.e("____ApiException",  "RequestBody : "+response.request.body.toString()+"\n"+"Response ---> "+response.request.method +" - "+ response.code + " - "+response.request.url+"\n"+e.message )

            }
        }
        return response
    }


}