package app.fintonic.demo.domain.repositories

import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.domain.models.response.BeerDto

interface BeersRepository {
//    REMOTE
    suspend fun getBeersList(page: String): Outcome<List<BeerDto>>
    suspend fun getBeer(beerId: String): Outcome<List<BeerDto>>
//    LOCAL
    suspend fun getBeersListDB(): Outcome<List<BeerDto>>
    suspend fun getBeerDB(beerId: String): Outcome<BeerDto>
    suspend fun addBeerDB(beer: BeerDto): Outcome<Boolean>
    suspend fun removeBeerDB(beerId: String): Outcome<Boolean>
}