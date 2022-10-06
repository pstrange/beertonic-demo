package app.fintonic.demo.domain.usecases

import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.repositories.BeersRepository

class DbGetBeerUseCase(private val repository: BeersRepository) {

    suspend operator fun invoke(beerId: String) = repository.getBeerDB(beerId)

}