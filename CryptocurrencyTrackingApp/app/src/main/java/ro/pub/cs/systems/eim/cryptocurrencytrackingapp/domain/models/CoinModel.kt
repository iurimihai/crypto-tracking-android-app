package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinModel(
        val coinId: String,
        val symbol: String,
        var refCurrency: String = Constants.DEFAULT_REF_CURRENCY
) {
    val price: Flow<Double> = flow {
        while (true) {
            val updatedPrice = CoinRepository.getCoinPrice(symbol + refCurrency)
            emit(updatedPrice)
            delay(Constants.REFRESH_PRICE_INTERVAL)
        }
    }
}