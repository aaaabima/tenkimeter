package id.aaaabima.tenkimeter.viewholder

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import id.aaaabima.tenkimeter.R
import id.aaaabima.tenkimeter.databinding.TableRowLayoutBinding
import id.aaaabima.tenkimeter.model.DailyHistoricalModel

/**
 * @author Abim (aaaabim@gmail.com)
 * @version DailyHistoricalViewHolder, v 0.1 7/18/2024 10:24 AM by Abim
 */
class DailyHistoricalViewHolder(private val binding: TableRowLayoutBinding, private val context:Context): RecyclerView.ViewHolder(binding.root) {
    fun onBind(dailyHistorical: DailyHistoricalModel, isHeader: Boolean) {
        if (isHeader) {
            binding.root.background = AppCompatResources.getDrawable(context, R.drawable.background_blue_rounded_black_8dp)
            binding.tvDay.text = context.getString(R.string.day)
            binding.tvWeather.text = context.getString(R.string.weather)
            binding.tvTemperature.text = context.getString(R.string.temperature)
        } else {
            binding.tvDay.text = dailyHistorical.day
            binding.tvWeather.text = dailyHistorical.weather
            binding.tvTemperature.text = context.getString(R.string.daily_temperature, dailyHistorical.temperature)
        }
    }
}