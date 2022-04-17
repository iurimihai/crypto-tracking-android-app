package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin

data class CoinResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)

fun CoinResponse.toCoin(): Coin {
    return Coin(
        coinId = id,
        name = name,
        symbol = symbol
    )
}
