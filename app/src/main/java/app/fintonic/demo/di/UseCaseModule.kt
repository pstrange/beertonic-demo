package app.fintonic.demo.di

import app.fintonic.demo.domain.usecases.*
import org.koin.dsl.module

val beersUseCasesModule = module {

    single { CountDownUseCase() }
    single { GetBeersUseCase(repository = get()) }
    single { GetBeerDetailUseCase(repository = get()) }

    single { DbGetBeerUseCase(repository = get()) }
    single { DbGetBeersListUseCase(repository = get()) }
    single { DbSaveBeerUseCase(repository = get()) }
    single { DbDeleteBeerUseCase(repository = get()) }

}