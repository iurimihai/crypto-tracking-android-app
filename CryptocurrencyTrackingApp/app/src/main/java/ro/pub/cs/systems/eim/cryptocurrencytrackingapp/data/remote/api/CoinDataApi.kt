package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_description.CoinDescriptionResponse
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker.CoinTickerResponse
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coins_list.CoinResponse

interface CoinDataApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinResponse>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDescription(@Path("coinId") coinId: String): CoinDescriptionResponse

    @GET("/v1/tickers/{coinId}")
    suspend fun getCoinPrice(@Path("coinId") coinId: String): CoinTickerResponse

    @GET("/v1/tickers/{coinId}/historical")
    suspend fun getCandlestickData(
            @Path("coinId") coinId: String,
            @Query("start") start: String,
            @Query("interval") interval: String
    )
}