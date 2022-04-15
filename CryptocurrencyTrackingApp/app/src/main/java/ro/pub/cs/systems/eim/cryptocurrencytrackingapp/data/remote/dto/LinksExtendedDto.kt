package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto

data class LinksExtendedDto(
    val statsDto: StatsDto,
    val type: String,
    val url: String
)