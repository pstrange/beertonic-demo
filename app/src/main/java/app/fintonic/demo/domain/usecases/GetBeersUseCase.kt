package app.fintonic.demo.domain.usecases

import app.fintonic.demo.domain.repositories.BeersRepository

class GetBeersUseCase(private val repository: BeersRepository) {

    data class Params(
        val page: String = "1"
    )

    suspend operator fun invoke(params: Params) = repository.getBeersList(params.page)

}