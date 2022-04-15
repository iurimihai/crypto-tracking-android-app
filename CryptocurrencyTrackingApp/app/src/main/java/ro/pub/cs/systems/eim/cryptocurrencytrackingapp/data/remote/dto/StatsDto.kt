package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto

data class StatsDto(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)