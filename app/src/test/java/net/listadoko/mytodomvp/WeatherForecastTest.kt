package net.listadoko.mytodomvp

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeatherForecastTest {
    @Mock
    lateinit var satellite: Satellite

    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenCloudy_returnsFalse() {
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.CLOUDY)
        weatherForecast = WeatherForecast(satellite)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenRainy_returnsTrue() {
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.RAINY)
        weatherForecast = WeatherForecast(satellite)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isTrue()
    }
}