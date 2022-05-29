package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

// TODO: error checks
class UpdatePriceUseCase(
    private val coinsRepository: CoinRepository
) {
//    operator fun invoke(coinSymbol: String, refCurrency: String): Flow<String> {
//        return flow {
//            while (currentCoroutineContext().isActive) {
//                val updatedPrice =
//                    coinsRepository.getCoinPrice(coinSymbol + refCurrency).price
//                emit(updatedPrice)
//                delay(Constants.REFRESH_PRICE_INTERVAL)
//            }
//        }.flowOn(Dispatchers.IO)
//    }

    operator fun invoke(coinId: String): Flow<String> {
        return flow {
            delay(1000L)
            while (currentCoroutineContext().isActive) {
                val updatedPrice =
                        coinsRepository.getCoinPrice(coinId).quotes.uSD.price.toString()
                emit(updatedPrice)
                delay(Constants.REFRESH_PRICE_INTERVAL)
            }
        }.flowOn(Dispatchers.IO)
    }
}