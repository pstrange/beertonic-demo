package app.fintonic.demo.data.mappers

import app.fintonic.demo.data.local.models.BeerDataModel
import app.fintonic.demo.data.utils.BaseMapper
import app.fintonic.demo.domain.models.response.BeerDto

class BeerToDBMapper : BaseMapper<BeerDto, BeerDataModel>{

    override fun mapFrom(from: BeerDto): BeerDataModel {
        return BeerDataModel(
            id = from.id,
            name = from.name,
            tagLine = from.tagLine,
            imageUrl = from.imageUrl
        )
    }

}