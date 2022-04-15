package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto

data class LinksDto(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    val source_code: List<String>,
    val website: List<String>,
    val youtube: List<String>
)