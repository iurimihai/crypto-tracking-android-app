package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

object RetrofitBuilder {
    private fun retrofitBuild(url: String): Retrofit.Builder {
        val builder: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
        }
        return builder
    }

    val dataApiService: CoinDataApi by lazy {
        retrofitBuild(Constants.COINS_DATA_BASE_URL)
            .build()
            .create(CoinDataApi::class.java)
    }

    val priceApiService: CoinPriceApi by lazy {
        retrofitBuild(Constants.COIN_PRICE_BASE_URL)
            .build()
            .create(CoinPriceApi::class.java)
    }
}