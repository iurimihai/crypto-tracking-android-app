package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class UpdatePriceUseCase(
    private val coinsRepository: CoinRepository
) {
    // TODO: check if can use liveData instead of flow
    suspend operator fun invoke(coinSymbol: String, refCurrency: String): Flow<String> {
        return flow {
            while (true) {
                val updatedPrice =
                    coinsRepository.getCoinPrice(coinSymbol + refCurrency).price

                emit(updatedPrice)
                delay(Constants.REFRESH_PRICE_INTERVAL)
            }
        }.flowOn(Dispatchers.IO)
    }
}