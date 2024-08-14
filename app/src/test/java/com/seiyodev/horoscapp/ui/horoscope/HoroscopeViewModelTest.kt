package com.seiyodev.horoscapp.ui.horoscope

import com.seiyodev.horoscapp.data.providers.HoroscopeProvider
import com.seiyodev.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopeViewModelTest{

    @MockK //Crea una instancia falsa de horoscopeProvider
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel:HoroscopeViewModel

    @Before //Ejecutar antes de cada test
    fun setUp(){
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewmodel is created then horoscopes are loaded`(){
        // Cada vez que se llame a getHoroscopes se devuelve una lista vacia
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList

        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscope= viewModel.horoscope.value

        assertTrue(horoscope.isNotEmpty())

    }

}