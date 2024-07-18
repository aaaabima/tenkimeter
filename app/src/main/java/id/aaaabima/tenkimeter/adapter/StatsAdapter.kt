package id.aaaabima.tenkimeter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aaaabima.tenkimeter.databinding.ItemStatsLayoutBinding
import id.aaaabima.tenkimeter.model.StatsModel
import id.aaaabima.tenkimeter.viewholder.StatsViewHolder

class StatsAdapter(private val statsList: List<StatsModel>): RecyclerView.Adapter<StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val binding = ItemStatsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding)
    }

    override fun getItemCount(): Int = statsList.size

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.onBind(statsList[position])
    }
}