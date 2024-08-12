package com.seiyodev.horoscapp.data.providers

import com.seiyodev.horoscapp.domain.model.HoroscopeInfo
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Aries
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Leo
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Libra
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.seiyodev.horoscapp.domain.model.HoroscopeInfo.Virgo
import javax.inject.Inject

class HoroscopeProvider @Inject constructor(){
    fun getHoroscopes():List<HoroscopeInfo>{
        return listOf(
            Aries, Taurus, Gemini,
            Cancer, Leo, Virgo,
            Libra, Scorpio, Sagittarius,
            Capricorn, Aquarius, Pisces
        )
    }
}