package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_chart


import com.google.gson.annotations.SerializedName

data class CoinChartData(
    @SerializedName("market_cap")
    val marketCap: Long,
    @SerializedName("price")
    val price: Double,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("volume_24h")
    val volume24h: Long
)