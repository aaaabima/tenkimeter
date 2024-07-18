package id.aaaabima.tenkimeter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aaaabima.tenkimeter.databinding.TableRowLayoutBinding
import id.aaaabima.tenkimeter.model.DailyHistoricalModel
import id.aaaabima.tenkimeter.viewholder.DailyHistoricalViewHolder

/**
 * @author Abim (aaaabim@gmail.com)
 * @version DailyHistoricalAdapter, v 0.1 7/18/2024 10:24 AM by Abim
 */
class DailyHistoricalAdapter(
    private val historicalData: List<DailyHistoricalModel>,
    private val context: Context
): RecyclerView.Adapter<DailyHistoricalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyHistoricalViewHolder {
        val binding = TableRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return DailyHistoricalViewHolder(binding, context)
    }

    override fun getItemCount(): Int = historicalData.size

    override fun onBindViewHolder(holder: DailyHistoricalViewHolder, position: Int) {
        if (position == 0)
            holder.onBind(DailyHistoricalModel(), true)
        else
            holder.onBind(historicalData[position], false)
    }
}