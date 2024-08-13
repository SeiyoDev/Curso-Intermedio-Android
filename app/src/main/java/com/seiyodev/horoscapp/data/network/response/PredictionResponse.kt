package com.seiyodev.horoscapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.seiyodev.horoscapp.domain.model.PredictionModel

data class PredictionResponse(
    @SerializedName("date") val date:String,
    @SerializedName("horoscope") val horoscope:String,
    @SerializedName("sign") val sign:String
){
    //Convertir a dominio
    fun toDomain():PredictionModel{

        return PredictionModel(horoscope = horoscope,
            sign = sign)
    }
}