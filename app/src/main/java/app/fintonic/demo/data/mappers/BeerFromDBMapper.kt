package app.fintonic.demo.data.mappers

import app.fintonic.demo.data.local.models.BeerDataModel
import app.fintonic.demo.data.utils.BaseMapper
import app.fintonic.demo.domain.models.response.BeerDto

class BeerFromDBMapper : BaseMapper<BeerDataModel, BeerDto>{

    override fun mapFrom(from: BeerDataModel): BeerDto {
        return BeerDto(
            idDb = from.beerId,
            id = from.id,
            name = from.name,
            tagLine = from.tagLine,
            imageUrl = from.imageUrl
        )
    }

}