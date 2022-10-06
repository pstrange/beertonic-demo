package app.fintonic.demo.di

import app.fintonic.demo.presentation.viewmodel.BeerDetailViewModel
import app.fintonic.demo.presentation.viewmodel.BeersViewModel
import app.fintonic.demo.presentation.viewmodel.FavoritesViewModel
import app.fintonic.demo.presentation.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val beersViewModelModule = module {

    viewModel { SplashViewModel(
        countDownUseCase = get()) }
    viewModel { BeersViewModel(
        getBeersUseCase = get()) }
    viewModel { BeerDetailViewModel(
        getBeerDetailUseCase = get(),
        dbSaveBeerUseCase = get(),
        dbGetBeerUseCase = get(),
        dbDeleteBeerUseCase = get()) }
    viewModel { FavoritesViewModel(
        dbGetBeersListUseCase = get()) }

}