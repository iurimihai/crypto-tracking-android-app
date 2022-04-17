package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.toCoinDescription
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.CoinDescription

class GetCoinDescriptionUseCase(
    private val coinsRepository: CoinRepository
) {
    suspend operator fun invoke(coinId: String): CoinDescription {
        return coinsRepository.getCoinDescription(coinId).toCoinDescription()
    }
}