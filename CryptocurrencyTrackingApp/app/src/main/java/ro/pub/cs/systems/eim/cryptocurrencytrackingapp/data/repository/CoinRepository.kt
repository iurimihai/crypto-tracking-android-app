package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.CoinDataApi
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinDataDto
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinPriceDto

class CoinRepository(val api: CoinDataApi) {
    fun getCoinData(coinId: String?): List<CoinDataDto>? {
        return null
    }

    fun getCoinPrice(coinId: String?): List<CoinPriceDto>? {
        return null
    }
}