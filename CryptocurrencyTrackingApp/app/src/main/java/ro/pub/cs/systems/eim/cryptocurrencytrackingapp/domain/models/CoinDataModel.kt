package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.*

data class CoinDataModel(
    val description: String,
    val developmentStatus: String,
    val hashAlgorithm: String,
    val id: String,
    val isActive: Boolean,
    val links: Links,
    val name: String,
    val openSource: Boolean,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<Team>
)