package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

data class Coin(
    val coinId: String,
    val name: String,
    val symbol: String,
    var price: String? = null
)