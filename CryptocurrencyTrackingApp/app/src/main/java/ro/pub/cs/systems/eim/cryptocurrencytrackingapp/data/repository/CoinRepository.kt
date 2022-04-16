package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api.RetrofitBuilder
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.*
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinModel
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinDataModel

object CoinRepository {
    suspend fun getCoins(): List<CoinModel> {
        return RetrofitBuilder.dataApiService.getCoins().map { it.toCoin() }
    }

    suspend fun getCoinData(coinId: String): CoinDataModel {
        return RetrofitBuilder.dataApiService.getCoinData(coinId).toCoinData()
    }

    suspend fun getCoinPrice(symbol: String): Double {
        return RetrofitBuilder.priceApiService.getUpdatedPrice(symbol).price.toDouble()
    }
}