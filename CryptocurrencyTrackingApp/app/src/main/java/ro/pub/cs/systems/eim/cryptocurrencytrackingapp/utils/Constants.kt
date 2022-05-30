package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils

object Constants {
    // CoinPaprika API provides general information about crypto coins
    const val COINS_DATA_BASE_URL = "https://api.coinpaprika.com/"

    // Binance API provides updated coin prices - DEPRECATED
    const val COIN_PRICE_BASE_URL = "https://api.binance.com/"

    const val COIN_ID = "coinId"
    const val COIN_NAME = "coinName"
    const val COIN_SYMBOL = "coinSymbol"

    const val FAVORITES = "favorites"

    const val REFRESH_PRICE_INTERVAL: Long = 60 * 1000 // 1min

    const val REGISTER = "REGISTER"
}