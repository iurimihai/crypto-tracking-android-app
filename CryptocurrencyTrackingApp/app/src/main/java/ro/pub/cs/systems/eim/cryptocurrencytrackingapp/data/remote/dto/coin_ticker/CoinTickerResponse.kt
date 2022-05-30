package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker


import com.google.gson.annotations.SerializedName
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinMarketData

data class CoinTickerResponse(
    @SerializedName("beta_value")
    val betaValue: Double?,
    @SerializedName("circulating_supply")
    val circulatingSupply: Long?,
    @SerializedName("first_data_at")
    val firstDataAt: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("max_supply")
    val maxSupply: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quotes")
    val quotes: Quotes?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("total_supply")
    val totalSupply: Long?
)

fun CoinTickerResponse.toCoinMarketData(): CoinMarketData {
    return CoinMarketData(
            circulatingSupply = this.circulatingSupply,
            maxSupply = this.maxSupply,
            totalSupply = this.totalSupply,
            id = this.id,
            name = this.name,
            symbol = this.symbol,
            price = this.quotes?.uSD?.price,
            percentChange1h = this.quotes?.uSD?.percentChange1h,
            percentChange24h = this.quotes?.uSD?.percentChange24h,
            percentChange7d = this.quotes?.uSD?.percentChange7d,
            marketCap = this.quotes?.uSD?.marketCap,
            volume24h = this.quotes?.uSD?.volume24h
    )
}