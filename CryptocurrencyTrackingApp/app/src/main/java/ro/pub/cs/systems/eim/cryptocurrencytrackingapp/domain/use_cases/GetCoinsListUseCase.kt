package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.use_cases

import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.remote.dto.toCoin
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.data.repository.CoinRepository
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.domain.models.Coin

// TODO: error checks
class GetCoinsListUseCase(
    private val coinsRepository: CoinRepository
) {
    suspend operator fun invoke(): List<Coin> {
        return coinsRepository.getCoins().map { it.toCoin() }
    }
}