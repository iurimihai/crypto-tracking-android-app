package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_description


import com.google.gson.annotations.SerializedName

data class Whitepaper(
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)