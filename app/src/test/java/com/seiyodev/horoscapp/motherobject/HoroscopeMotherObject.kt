package com.seiyodev.horoscapp.motherobject

import com.seiyodev.horoscapp.data.network.response.PredictionResponse
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.*

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "taurus")
    val horoscopeInfoList = listOf(
        Aries, Taurus, Gemini,
        Cancer, Leo, Virgo,
        Libra, Scorpio, Sagittarius,
        Capricorn, Aquarius, Pisces
    )
}