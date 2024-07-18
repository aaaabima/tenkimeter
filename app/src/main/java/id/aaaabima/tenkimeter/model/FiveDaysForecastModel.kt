package id.aaaabima.tenkimeter.model

import com.google.gson.annotations.SerializedName

data class FiveDaysForecastModel(

	@field:SerializedName("city")
	val city: City,

	@field:SerializedName("cnt")
	val cnt: Int,

	@field:SerializedName("cod")
	val cod: String,

	@field:SerializedName("message")
	val message: Int,

	@field:SerializedName("list")
	val list: List<ListItem>
)

data class Rain(

	@field:SerializedName("3h")
	val jsonMember3h: Double
)

data class ListItem(

	@field:SerializedName("dt")
	val dt: Int,

	@field:SerializedName("pop")
	val pop: Double,

	@field:SerializedName("visibility")
	val visibility: Int,

	@field:SerializedName("dt_txt")
	val dtTxt: String,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>,

	@field:SerializedName("main")
	val main: Main,

	@field:SerializedName("clouds")
	val clouds: Clouds,

	@field:SerializedName("sys")
	val sys: Sys,

	@field:SerializedName("wind")
	val wind: Wind,

	@field:SerializedName("rain")
	val rain: Rain
)

data class City(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("coord")
	val coord: Coord,

	@field:SerializedName("sunrise")
	val sunrise: Int,

	@field:SerializedName("timezone")
	val timezone: Int,

	@field:SerializedName("sunset")
	val sunset: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("population")
	val population: Int
)
