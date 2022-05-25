package StructuralPattern

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

interface Temperature {
    var temperature: Double
}

class CelsiusTemperature(override var temperature: Double) : Temperature

class FahrenheitTemperature(private var celsiusTemperature: CelsiusTemperature) : Temperature {

    override var temperature: Double
    get() = convertCelsiusToFahrenheit(celsiusTemperature.temperature)
    set(temperatureInF) {
        celsiusTemperature.temperature = convertFahrenheitToCelsius(temperatureInF)
    }

    private fun convertCelsiusToFahrenheit(c: Double): Double =
        ((BigDecimal.valueOf(c).setScale(2) * BigDecimal(9) / BigDecimal(5)) + BigDecimal(32)).toDouble()

    private fun convertFahrenheitToCelsius(f: Double): Double =
        ((BigDecimal.valueOf(f).setScale(2) - BigDecimal(32)) * BigDecimal(5) / BigDecimal(9)).toDouble()
}

class AdapterTest {

    @Test
    fun Adapter() {
        val celsiusTemperature = CelsiusTemperature(0.0)
        val fahrenheitTemperature = FahrenheitTemperature(celsiusTemperature)

        celsiusTemperature.temperature = 36.6
        println("${celsiusTemperature.temperature} C -> ${fahrenheitTemperature.temperature} F")

        assertEquals(fahrenheitTemperature.temperature.toDouble(), 97.88)

        fahrenheitTemperature.temperature = 100.0
        println("${fahrenheitTemperature.temperature} F -> ${celsiusTemperature.temperature} C")

        assertEquals(celsiusTemperature.temperature.toDouble(), 37.78)
    }
}