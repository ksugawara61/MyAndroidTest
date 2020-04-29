package net.listadoko.mytodomvp

class Satellite {
    fun getWeather(latitude: Double, longitude: Double): Weather {
        return Weather.SUNNY
    }
}

class WeatherForecast(val satellite: Satellite) {
    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather(0.0, 0.0)
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }
}

enum class Weather {
    SUNNY, CLOUDY, RAINY
}