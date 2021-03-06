package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_description


import com.google.gson.annotations.SerializedName

data class LinksExtended(
        @SerializedName("stats")
    val stats: Stats,
        @SerializedName("type")
    val type: String,
        @SerializedName("url")
    val url: String
)