package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api.CoinApiBuilder
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_chart.CoinChartData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_description.CoinDescriptionResponse
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker.CoinTickerResponse
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coins_list.CoinResponse

object CoinRepository {
    suspend fun getCoins(): List<CoinResponse> {
        return CoinApiBuilder.coinApiService.getCoins()
    }

    suspend fun getCoinMarketData(coinId: String): CoinTickerResponse {
        return CoinApiBuilder.coinApiService.getCoinPrice(coinId)
    }

    suspend fun getCoinDescription(coinId: String): CoinDescriptionResponse {
        return CoinApiBuilder.coinApiService.getCoinDescription(coinId)
    }

//    suspend fun getCoinPrice(symbol: String): CoinPriceResponse {
//        return CoinApiBuilder.priceApiService.getUpdatedPrice(symbol)
//    }

    suspend fun getCoinPrice(coinId: String): CoinTickerResponse {
        return CoinApiBuilder.coinApiService.getCoinPrice(coinId)
    }

    suspend fun getChartData(
        coinId: String,
        start: String,
        interval: String
    ): List<CoinChartData> {
        return CoinApiBuilder.coinApiService.getChartData(coinId, start, interval)
    }
}