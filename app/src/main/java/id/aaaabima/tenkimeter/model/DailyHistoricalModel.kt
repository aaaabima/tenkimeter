package id.aaaabima.tenkimeter.model

import java.text.SimpleDateFormat

/**
 * @author Abim (aaaabim@gmail.com)
 * @version DailyHistoricalModel, v 0.1 7/18/2024 10:26 AM by Abim
 */
data class DailyHistoricalModel(
    val day: String = "",
    val weather: String = "",
    val temperature: Double = 0.0
)

fun ListItem.toDailyHistoricalModel() = DailyHistoricalModel(
    day = SimpleDateFormat("EEE").format(dt.toLong().times(1000)),
    weather = weather.first().main,
    temperature = main.temp.minus(273)
)

fun List<ListItem>.toListDailyHistoricalModel() = map { it.toDailyHistoricalModel() }