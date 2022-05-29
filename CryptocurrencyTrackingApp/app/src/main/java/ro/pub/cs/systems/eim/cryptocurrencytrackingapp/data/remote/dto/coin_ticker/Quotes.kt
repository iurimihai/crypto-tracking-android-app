package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_ticker


import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("USD")
    val uSD: USD
)