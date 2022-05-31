package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_chart.CoinChartData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker.toCoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinMarketData
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource
import java.io.IOException

class GetChartDataUseCase(
    private val coinsRepository: CoinRepository
) {
    operator fun invoke(
        coinId: String,
        start: String,
        interval: String
    ): Flow<Resource<List<CoinChartData>>> = flow {
        try {
            emit(Resource.Loading<List<CoinChartData>>())
            val coinChartData = coinsRepository.getChartData(coinId, start, interval)
            emit(Resource.Success(coinChartData))
        } catch (e: HttpException) {
            emit(Resource.Error<List<CoinChartData>>(e.localizedMessage ?: "HTTP Error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CoinChartData>>("IO Error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}