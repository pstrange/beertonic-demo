package app.fintonic.demo.presentation

import android.app.Application
import app.fintonic.demo.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppBeerTonic : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppBeerTonic)
            modules(
                serviceModule,
                mappersModule,
                beersRepositoriesModule,
                beersUseCasesModule,
                beersViewModelModule)
        }
    }

}