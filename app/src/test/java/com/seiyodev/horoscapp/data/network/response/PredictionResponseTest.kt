package com.seiyodev.horoscapp.data.network.response

import com.seiyodev.horoscapp.motherobject.HoroscopeMotherObject
import com.seiyodev.horoscapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest{

    @Test
    fun `toDomain Should Return A Correct PredictionModel`(){
        //Given
        val horoscopeResponse = anyResponse.copy(sign = "libra")

        //When
        val predictionModel = horoscopeResponse.toDomain()

        //Then
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope

    }
}