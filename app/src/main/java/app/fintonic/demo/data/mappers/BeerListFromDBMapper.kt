package app.fintonic.demo.data.mappers

import app.fintonic.demo.data.local.models.BeerDataModel
import app.fintonic.demo.data.utils.BaseMapper
import app.fintonic.demo.domain.models.response.BeerDto

class BeerListFromDBMapper(private val beerDbMapper: BeerFromDBMapper) : BaseMapper<List<BeerDataModel>?, List<BeerDto>?>{

    override fun mapFrom(from: List<BeerDataModel>?): List<BeerDto>? {
        return from?.map { beerDbMapper.mapFrom(it) }
    }

}