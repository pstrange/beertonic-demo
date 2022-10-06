package app.fintonic.demo.data.local

import androidx.room.*
import app.fintonic.demo.data.local.models.BeerDataModel

@Dao
interface BeersFavDao {
    @Query("select * from beersFavTable")
    fun getAllBeer() : List<BeerDataModel>

    @Query("select * from beersFavTable where remoteId is :idBeer")
    fun findBeer(idBeer: String): BeerDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBeer(addBeer: BeerDataModel)

    @Query("DELETE FROM beersFavTable WHERE remoteId is :idBeer")
    fun deleteBeer(idBeer: String)
}