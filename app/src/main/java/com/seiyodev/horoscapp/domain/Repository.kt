package com.seiyodev.horoscapp.domain

import com.seiyodev.horoscapp.data.network.response.PredictionResponse
import com.seiyodev.horoscapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}