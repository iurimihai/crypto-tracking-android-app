package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinDescriptionResponse
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinResponse

interface CoinDataApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinResponse>

    @GET("coins/{coinId}")
    suspend fun getCoinDescription(@Path("coinId") coinId: String): CoinDescriptionResponse

}