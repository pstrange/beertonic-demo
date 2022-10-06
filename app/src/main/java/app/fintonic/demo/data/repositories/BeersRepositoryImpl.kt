package app.fintonic.demo.data.repositories

import app.fintonic.demo.data.local.BeersFavDao
import app.fintonic.demo.data.mappers.BeerListFromDBMapper
import app.fintonic.demo.data.mappers.BeerToDBMapper
import app.fintonic.demo.data.mappers.BeersMapper
import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.data.local.models.BeerDataModel
import app.fintonic.demo.data.mappers.BeerFromDBMapper
import app.fintonic.demo.data.remote.models.response.Beer
import app.fintonic.demo.data.remote.BeersApi
import app.fintonic.demo.data.utils.ApiDataSource
import app.fintonic.demo.data.utils.LocalDataSource
import app.fintonic.demo.data.utils.asOutcome
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.repositories.BeersRepository

class BeersRepositoryImpl(
    private val beerMapper: BeersMapper,
    private val beerToDbMapper: BeerToDBMapper,
    private val beerFromDBMapper: BeerFromDBMapper,
    private val beerListFromDbMapper: BeerListFromDBMapper,
    private val service: BeersApi,
    private val beersDao: BeersFavDao) : BeersRepository {

    override suspend fun getBeersList(page: String) =
        object : ApiDataSource<List<Beer>, List<BeerDto>>() {
            override suspend fun consumeService() = service.getBeersList(page)
            override fun getOutcome(body: List<Beer>) = beerMapper.mapFrom(body).asOutcome()
        }.consume()

    override suspend fun getBeer(beerId: String) =
        object : ApiDataSource<List<Beer>, List<BeerDto>>() {
            override suspend fun consumeService() = service.getBeer(beerId)
            override fun getOutcome(body: List<Beer>) = beerMapper.mapFrom(body).asOutcome()
        }.consume()

    override suspend fun addBeerDB(beer: BeerDto) =
        object : LocalDataSource<Any, Boolean>(){
            override suspend fun consumeService() = beersDao.addBeer(beerToDbMapper.mapFrom(beer))
            override fun getOutcome(result: Any) = true.asOutcome()
        }.consume()


    override suspend fun getBeersListDB() =
        object : LocalDataSource<List<BeerDataModel>, List<BeerDto>>(){
            override suspend fun consumeService() = beersDao.getAllBeer()
            override fun getOutcome(result: List<BeerDataModel>) = beerListFromDbMapper.mapFrom(result).asOutcome()
        }.consume()

    override suspend fun removeBeerDB(beerId: String) =
        object : LocalDataSource<Any, Boolean>(){
            override suspend fun consumeService() = beersDao.deleteBeer(beerId)
            override fun getOutcome(result: Any) = true.asOutcome()
        }.consume()

    override suspend fun getBeerDB(beerId: String) =
        object : LocalDataSource<BeerDataModel, BeerDto>(){
            override suspend fun consumeService() = beersDao.findBeer(beerId)
            override fun getOutcome(result: BeerDataModel) = beerFromDBMapper.mapFrom(result).asOutcome()
        }.consume()
}