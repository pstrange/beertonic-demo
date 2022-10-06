package app.fintonic.demo.domain.usecases

import app.fintonic.demo.domain.repositories.BeersRepository

class GetBeerDetailUseCase(private val repository: BeersRepository) {

    data class Params(
        val beerId: String = "1"
    )

    suspend operator fun invoke(params: Params) = repository.getBeer(params.beerId)

}