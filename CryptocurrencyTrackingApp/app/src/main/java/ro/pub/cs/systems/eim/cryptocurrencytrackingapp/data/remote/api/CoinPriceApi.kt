package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.CoinPriceDto

interface CoinPriceApi {

    @GET("/api/v3/ticker/price")
    suspend fun getUpdatedPrice(@Query("symbol") symbol: String): CoinPriceDto
}