package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api.CoinApiBuilder
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.*
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinDescription

object CoinRepository {
    suspend fun getCoins(): List<CoinResponse> {
        return CoinApiBuilder.descriptionApiService.getCoins()
    }

    suspend fun getCoinDescription(coinId: String): CoinDescriptionResponse {
        return CoinApiBuilder.descriptionApiService.getCoinDescription(coinId)
    }

    suspend fun getCoinPrice(symbol: String): CoinPriceResponse {
        return CoinApiBuilder.priceApiService.getUpdatedPrice(symbol)
    }
}