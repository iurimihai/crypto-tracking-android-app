package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinDataDto
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinDto
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinPriceDto

interface CoinDataApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("coins/{coinId}")
    suspend fun getCoinData(@Path("coinId") coinId: String): CoinDataDto

}