package app.fintonic.demo.di

import android.content.Context
import app.fintonic.demo.data.local.BeersDb
import app.fintonic.demo.data.local.BeersFavDao
import app.fintonic.demo.data.mappers.BeerFromDBMapper
import app.fintonic.demo.data.mappers.BeerListFromDBMapper
import app.fintonic.demo.data.mappers.BeerToDBMapper
import app.fintonic.demo.data.mappers.BeersMapper
import app.fintonic.demo.data.repositories.BeersRepositoryImpl
import app.fintonic.demo.domain.repositories.BeersRepository
import org.koin.dsl.module

val mappersModule = module {

    single { BeersMapper() }
    single { BeerToDBMapper() }
    single { BeerFromDBMapper() }
    single { BeerListFromDBMapper(beerDbMapper = get()) }

}

val beersRepositoriesModule = module {

    single { provideBeerFavDao(context = get()) }
    single<BeersRepository> { BeersRepositoryImpl(
        beerMapper = get(),
        beerToDbMapper = get(),
        beerFromDBMapper = get(),
        beerListFromDbMapper = get(),
        service = get(),
        beersDao = get()) }

}

private fun provideBeerFavDao(context: Context): BeersFavDao? =
    BeersDb.getInstance(context)?.getBeersFavDao()