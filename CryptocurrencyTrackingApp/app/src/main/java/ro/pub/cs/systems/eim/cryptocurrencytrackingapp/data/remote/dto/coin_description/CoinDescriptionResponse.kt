package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.coin_description


import com.google.gson.annotations.SerializedName
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinDescription

data class CoinDescriptionResponse(
        @SerializedName("description")
    val description: String,
        @SerializedName("development_status")
    val developmentStatus: String,
        @SerializedName("first_data_at")
    val firstDataAt: String,
        @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
        @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
        @SerializedName("id")
    val id: String,
        @SerializedName("is_active")
    val isActive: Boolean,
        @SerializedName("is_new")
    val isNew: Boolean,
        @SerializedName("last_data_at")
    val lastDataAt: String,
        @SerializedName("links")
    val links: Links,
        @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>,
        @SerializedName("message")
    val message: String,
        @SerializedName("name")
    val name: String,
        @SerializedName("open_source")
    val openSource: Boolean,
        @SerializedName("org_structure")
    val orgStructure: String,
        @SerializedName("proof_type")
    val proofType: String,
        @SerializedName("rank")
    val rank: Int,
        @SerializedName("started_at")
    val startedAt: String,
        @SerializedName("symbol")
    val symbol: String,
        @SerializedName("tags")
    val tags: List<Tag>,
        @SerializedName("team")
    val team: List<Team>,
        @SerializedName("type")
    val type: String,
        @SerializedName("whitepaper")
    val whitepaper: Whitepaper
)

fun CoinDescriptionResponse.toCoinDescription(): CoinDescription {
    return CoinDescription(
        description = description,
        developmentStatus = developmentStatus,
        hashAlgorithm = hashAlgorithm,
        id = id,
        isActive = isActive,
        links = links,
        name = name,
        openSource = openSource,
        symbol = symbol,
        tags = tags,
        team = team
    )
}