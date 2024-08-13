package com.seiyodev.horoscapp.data.network

import com.seiyodev.horoscapp.BuildConfig.BASE_URL
import com.seiyodev.horoscapp.data.RepositoryImpl
import com.seiyodev.horoscapp.data.core.interceptors.AuthInterceptor
import com.seiyodev.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Esto nos permite proveer retrofit a cualquier clase que necesite
    @Provides
    @Singleton // Permite tener una unica instancia de retrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Esto nos permite proveer el interceptor a cualquier clase que necesite
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor):OkHttpClient{

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)// Nos permite ver el body de la respuesta en el Log
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideHoroscope(retrofit:Retrofit):HoroscopeApiService{
        return retrofit.create(HoroscopeApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository{
        return RepositoryImpl(apiService)
    }



}