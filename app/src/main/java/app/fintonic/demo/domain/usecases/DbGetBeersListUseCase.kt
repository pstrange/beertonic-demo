package app.fintonic.demo.domain.usecases

import app.fintonic.demo.domain.repositories.BeersRepository

class DbGetBeersListUseCase(private val repository: BeersRepository) {

    suspend operator fun invoke() = repository.getBeersListDB()

}