package com.seiyodev.horoscapp.data

import android.util.Log
import com.seiyodev.horoscapp.data.network.HoroscopeApiService
import com.seiyodev.horoscapp.data.network.response.PredictionResponse
import com.seiyodev.horoscapp.domain.Repository
import com.seiyodev.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        //Peticion Retrofit
       runCatching { apiService.getHoroscope(sign) }

           //Si sale bien
           .onSuccess { return it.toDomain() }

           //Si sale mal
           .onFailure { Log.i("seiyo", "Ha ocurrido un error ${it.message}") }

        //Si no sale nada
        return null

    }
}