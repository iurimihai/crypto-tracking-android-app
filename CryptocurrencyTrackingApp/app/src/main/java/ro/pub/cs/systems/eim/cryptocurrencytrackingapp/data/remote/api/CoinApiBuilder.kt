package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.api

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

object CoinApiBuilder {
    private fun retrofitBuild(url: String): Retrofit.Builder {
        val builder: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpLogger())
        }
        return builder
    }

    fun httpLogger(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    val coinApiService: CoinDataApi by lazy {
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