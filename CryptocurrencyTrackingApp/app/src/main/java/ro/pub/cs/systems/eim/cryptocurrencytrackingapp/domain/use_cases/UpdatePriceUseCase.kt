package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
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
    operator fun invoke(coinSymbol: String, refCurrency: String): LiveData<String> {
        return liveData(Dispatchers.IO) {
            while (currentCoroutineContext().isActive) {
                val updatedPrice =
                    coinsRepository.getCoinPrice(coinSymbol + refCurrency).price
                emit(updatedPrice)
                delay(Constants.REFRESH_PRICE_INTERVAL)
            }
        }
    }
}