package app.fintonic.demo.domain.usecases

import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.repositories.BeersRepository

class DbDeleteBeerUseCase(private val repository: BeersRepository) {

    suspend operator fun invoke(params: String) = repository.removeBeerDB(params)

}