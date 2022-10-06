package app.fintonic.demo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.fintonic.demo.BuildConfig
import app.fintonic.demo.data.local.models.BeerDataModel

@Database(entities = [BeerDataModel::class], version = 1, exportSchema = false)
abstract class BeersDb : RoomDatabase() {
    abstract fun getBeersFavDao() : BeersFavDao

    companion object{
        private const val DATABASE_NAME = BuildConfig.DB_NAME
        @Volatile
        private var INSTANCE: BeersDb? = null

        fun getInstance(context: Context): BeersDb? {
            INSTANCE ?: synchronized(this) {
                INSTANCE =
                    Room.databaseBuilder(
                        context.applicationContext, BeersDb::class.java, DATABASE_NAME)
                        //las bases de datos actuales se usan como caché, se pueden borrar directamente
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE
        }
    }
}