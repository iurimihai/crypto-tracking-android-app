package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker


import com.google.gson.annotations.SerializedName

data class CoinTickerResponse(
    @SerializedName("beta_value")
    val betaValue: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Long,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("max_supply")
    val maxSupply: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("quotes")
    val quotes: Quotes,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: Long
)