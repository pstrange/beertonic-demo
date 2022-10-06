package app.fintonic.demo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "beersFavTable", indices = [Index(value = ["remoteId"], unique = true)])
data class BeerDataModel(
    @PrimaryKey(autoGenerate = true)
    var beerId: Int = 0,
    @ColumnInfo(name = "remoteId")
    var id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "tagline")
    val tagLine: String,
    @ColumnInfo(name = "imageurl")
    val imageUrl: String
)