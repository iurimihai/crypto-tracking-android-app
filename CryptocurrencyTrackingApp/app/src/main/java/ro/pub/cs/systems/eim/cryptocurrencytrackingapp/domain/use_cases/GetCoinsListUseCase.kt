package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.toCoin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Resource
import java.io.IOException

// TODO: error checks
class GetCoinsListUseCase(
    private val coinsRepository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinsRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "HTTP Error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("IO Error occurred"))
        }
    }.flowOn(Dispatchers.IO)

//    operator fun invoke(): List<Coin> = withContext(Dispatchers.IO) {
//        coinsRepository.getCoins().map { it.toCoin() }
//    }
}