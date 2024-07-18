package id.aaaabima.tenkimeter.util

import android.util.Log
import com.github.mikephil.charting.formatter.ValueFormatter

/**
 * @author Abim (aaaabim@gmail.com)
 * @version AxisDateFormatter, v 0.1 7/17/2024 10:09 PM by Abim
 */
class AxisDateFormatter(private val values: List<String>): ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return if (value >= 0) {
            if (values.size > value.toInt()) {
                values[value.toInt()]
            } else ""
        } else {
            ""
        }
    }
}