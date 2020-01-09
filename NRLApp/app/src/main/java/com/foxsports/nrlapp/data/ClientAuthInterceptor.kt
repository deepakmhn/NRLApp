package com.foxsports.nrlapp.data

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * An Interceptor that adds an user key to requests
 */
class ClientAuthInterceptor(private val userKey: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val url = chain.request().url().newBuilder()
            .addQueryParameter("userkey", userKey).build()
        requestBuilder.url(url)
        return chain.proceed(requestBuilder.build())
    }
}