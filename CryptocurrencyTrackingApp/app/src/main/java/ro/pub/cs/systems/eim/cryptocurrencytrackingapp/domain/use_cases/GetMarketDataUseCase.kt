package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import retrofit2.HttpException
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker.toCoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource
import java.io.IOException

class GetMarketDataUseCase(
        private val coinsRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinMarketData>> = flow {
        while (currentCoroutineContext().isActive) {
            try {
                emit(Resource.Loading<CoinMarketData>())
                val coinMarketData = coinsRepository.getCoinMarketData(coinId).toCoinMarketData()
                emit(Resource.Success(coinMarketData))
            } catch (e: HttpException) {
                emit(Resource.Error<CoinMarketData>(e.localizedMessage ?: "HTTP Error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error<CoinMarketData>("IO Error occurred"))
            }
            delay(Constants.REFRESH_PRICE_INTERVAL)
        }
    }.flowOn(Dispatchers.IO)
}