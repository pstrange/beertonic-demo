package app.fintonic.demo.data.remote

import app.fintonic.demo.data.remote.models.response.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeersApi {

    @GET("beers")
    suspend fun getBeersList(@Query("page") page: String) : Response<List<Beer>>

    @GET("beers/{beerId}")
    suspend fun getBeer(@Path("beerId") beerId: String) : Response<List<Beer>>

}