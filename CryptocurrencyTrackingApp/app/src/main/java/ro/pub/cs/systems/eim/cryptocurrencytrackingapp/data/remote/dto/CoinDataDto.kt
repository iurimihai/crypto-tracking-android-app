package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto

data class CoinDataDto(
    val description: String,
    val development_status: String,
    val first_data_at: String,
    val hardware_wallet: Boolean,
    val hash_algorithm: String,
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val last_data_at: String,
    val linksDto: LinksDto,
    val links_extendedDto: List<LinksExtendedDto>,
    val message: String,
    val name: String,
    val open_source: Boolean,
    val org_structure: String,
    val proof_type: String,
    val rank: Int,
    val started_at: String,
    val symbol: String,
    val tagDtos: List<TagDto>,
    val teamDto: List<TeamDto>,
    val type: String,
    val whitepaperDto: WhitepaperDto
)