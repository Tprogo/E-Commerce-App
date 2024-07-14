package com.example.e_comapplication.okhttp

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val credential = Credentials.basic("ck_1a80e20f77250a965909313891a90302c6621251", "cs_1405b9e8cb57acdf6834af637fbe38f3da89619e")
        request.addHeader("Authorization", credential)

        return chain.proceed(request.build())
    }
}