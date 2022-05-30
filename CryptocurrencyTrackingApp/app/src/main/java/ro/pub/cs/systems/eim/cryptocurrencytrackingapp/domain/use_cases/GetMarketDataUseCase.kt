package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker.toCoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource
import java.io.IOException

class GetMarketDataUseCase(
        private val coinsRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinMarketData>> = flow {
        try {
            emit(Resource.Loading<CoinMarketData>())
            val coinMarketData = coinsRepository.getCoinMarketData(coinId).toCoinMarketData()
            Log.d("MARKETDATAUSECASE", "AWdwad")
            emit(Resource.Success(coinMarketData))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinMarketData>(e.localizedMessage ?: "HTTP Error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinMarketData>("IO Error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}