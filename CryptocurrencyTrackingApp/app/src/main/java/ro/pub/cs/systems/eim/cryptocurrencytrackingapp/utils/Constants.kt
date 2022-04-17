package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils

object Constants {
    // CoinPaprika API provides general information about crypto coins
    const val COINS_DATA_BASE_URL = "https://api.coinpaprika.com/v1"

    // Binance API provides updated coin prices
    const val COIN_PRICE_BASE_URL = "https://api.binance.com"

    // By default, prices will be shown in USD
    const val DEFAULT_REF_CURRENCY = "USDT"

    const val REFRESH_PRICE_INTERVAL: Long = 5000
}