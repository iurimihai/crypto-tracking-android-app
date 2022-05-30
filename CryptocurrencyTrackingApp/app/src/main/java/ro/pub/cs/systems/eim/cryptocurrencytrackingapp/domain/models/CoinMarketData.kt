package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models

data class CoinMarketData(
        val circulatingSupply: Long?,
        val id: String?,
        val maxSupply: Long?,
        val name: String?,
        val symbol: String?,
        val totalSupply: Long?,
        val percentChange1h: Double?,
        val percentChange24h: Double?,
        val percentChange7d: Double?,
        val price: Double?,
        val marketCap: Long?,
        val volume24h: Double?,
)