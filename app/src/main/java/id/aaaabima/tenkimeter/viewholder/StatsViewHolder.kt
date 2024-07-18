package id.aaaabima.tenkimeter.viewholder

import androidx.recyclerview.widget.RecyclerView
import id.aaaabima.tenkimeter.databinding.ItemStatsLayoutBinding
import id.aaaabima.tenkimeter.model.StatsModel

/**
 * @author Abim (aaaabim@gmail.com)
 * @version StatsViewHolder, v 0.1 7/17/2024 7:05 PM by Abim
 */
class StatsViewHolder(private val binding: ItemStatsLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(stats: StatsModel) {
        binding.statsTitle.text = "${stats.title} : ${stats.value} "
    }
}