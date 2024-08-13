package com.seiyodev.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager):Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .header("Autorization", tokenManager.getToken()) // Aqui va el token
            .build()

        return chain.proceed(request)
    }
}

// Esta clase nos permite obtener el token. En apps reales esta clase deberia ir a parte.
class TokenManager @Inject constructor(){
    fun getToken():String = "Hello World"
}