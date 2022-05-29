package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin

data class CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)