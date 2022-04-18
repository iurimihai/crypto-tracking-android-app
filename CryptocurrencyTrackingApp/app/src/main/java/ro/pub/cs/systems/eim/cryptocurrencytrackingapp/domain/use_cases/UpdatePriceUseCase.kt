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

class UpdatePriceUseCase(
    private val coinsRepository: CoinRepository
) {
    suspend operator fun invoke(coinSymbol: String, refCurrency: String): Flow<String> {
        return flow {
            while (currentCoroutineContext().isActive) {
                val updatedPrice =
                    coinsRepository.getCoinPrice(coinSymbol + refCurrency).price
                emit(updatedPrice)
                delay(Constants.REFRESH_PRICE_INTERVAL)
            }
        }.flowOn(Dispatchers.IO)
    }
}