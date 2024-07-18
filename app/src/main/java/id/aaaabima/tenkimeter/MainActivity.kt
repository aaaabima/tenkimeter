package id.aaaabima.tenkimeter

import android.R
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.aaaabima.tenkimeter.adapter.DailyHistoricalAdapter
import id.aaaabima.tenkimeter.adapter.StatsAdapter
import id.aaaabima.tenkimeter.databinding.ActivityMainBinding
import id.aaaabima.tenkimeter.model.DailyHistoricalModel
import id.aaaabima.tenkimeter.model.FiveDaysForecastModel
import id.aaaabima.tenkimeter.model.StatsModel
import id.aaaabima.tenkimeter.model.WeatherModel
import id.aaaabima.tenkimeter.model.toListDailyHistoricalModel
import id.aaaabima.tenkimeter.util.AxisDateFormatter
import id.aaaabima.tenkimeter.util.readJsonFromAssets
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var statsAdapter: StatsAdapter? = null
    private var dailyHistoricalAdapter: DailyHistoricalAdapter? = null
    private val lat = -6.162250
    private val lon = 106.741700
    private val apiKey = "8ca4eeaf959762801c504912728c77c9 "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTopStatsData()
        getHourlyWeatherData()
        getFiveDaysForecastWeatherData()
    }

    private fun getTopStatsData() {
        val jsonString = readJsonFromAssets(this, "stats.json")
        val statsModelListType = object : TypeToken<List<StatsModel>>() {}.type
        val data: List<StatsModel> = Gson().fromJson(jsonString, statsModelListType)
        initTopStatsAdapter(data)
    }

    private fun getHourlyWeatherData() {
        val jsonString = readJsonFromAssets(this, "hourlyWeather.json")
        val weatherModelListType = object : TypeToken<List<WeatherModel>>() {}.type
        val data: List<WeatherModel> = Gson().fromJson(jsonString, weatherModelListType)
        initHourlyWeatherBar(data.take(8))
    }

    private fun initTopStatsAdapter(list: List<StatsModel>) {
        statsAdapter = StatsAdapter(list)
        binding.rvTopStats.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopStats.adapter = statsAdapter
    }

    private fun initHourlyWeatherBar(list: List<WeatherModel>) {
        binding.bcHourlyStats.apply {
            description.isEnabled = false
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            setPinchZoom(false)
            setDrawGridBackground(false)
            legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.setDrawInside(false)
            legend.form = Legend.LegendForm.SQUARE
            legend.formSize = 9f
            legend.textSize = 12f
            legend.xEntrySpace = 4f
        }
        setBarChartData(list)
    }

    private fun setBarChartData(list: List<WeatherModel>) {
        val entries: ArrayList<BarEntry> = ArrayList()
        val hourEntries: ArrayList<String> = ArrayList()
        var counter = 0
        list.forEach { hourlyWeather ->
            entries.add(BarEntry(counter.toFloat(), hourlyWeather.main.temp.minus(273).toFloat()))
            hourEntries.add(SimpleDateFormat("HH:mm").format(hourlyWeather.dt.toLong().times(1000)))
            counter++
        }

        val hour = AxisDateFormatter(hourEntries)
        val barDataSet = BarDataSet(entries, "Temperature (°C)")
        barDataSet.setDrawValues(false)

        val dataSet: ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(barDataSet)

        val newData = BarData(dataSet)
        newData.barWidth = 0.9f
        binding.bcHourlyStats.data = newData
        binding.bcHourlyStats.xAxis.valueFormatter = hour
        binding.bcHourlyStats.invalidate()
    }

    private fun getFiveDaysForecastWeatherData() {
        val jsonString = readJsonFromAssets(this, "fiveDaysForecast.json")
        val fiveDaysForecastModelType = object : TypeToken<FiveDaysForecastModel>() {}.type
        val data: FiveDaysForecastModel = Gson().fromJson(jsonString, fiveDaysForecastModelType)
        val historicalData = data.list.toListDailyHistoricalModel()
        setDailyHistoricalData(historicalData)
    }

    private fun setDailyHistoricalData(list: List<DailyHistoricalModel>) {
        val cleanData = getDailyHistoricalHeader()
        cleanData.addAll(list.distinctBy { it.day })
        initDailyHistoricalAdapter(cleanData)
    }

    private fun initDailyHistoricalAdapter(list: List<DailyHistoricalModel>) {
        dailyHistoricalAdapter = DailyHistoricalAdapter(list, this)
        binding.rvDailyHistorical.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDailyHistorical.adapter = dailyHistoricalAdapter
        binding.rvDailyHistorical.isNestedScrollingEnabled = false
    }

    private fun getDailyHistoricalHeader() = arrayListOf(DailyHistoricalModel())
}