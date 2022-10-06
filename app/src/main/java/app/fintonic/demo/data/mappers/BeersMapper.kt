package app.fintonic.demo.data.mappers

import app.fintonic.demo.data.remote.models.response.Amount
import app.fintonic.demo.data.remote.models.response.Beer
import app.fintonic.demo.data.remote.models.response.Ingredient
import app.fintonic.demo.data.remote.models.response.IngredientList
import app.fintonic.demo.data.utils.BaseMapper
import app.fintonic.demo.domain.models.response.AmountDto
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.models.response.IngredientDto
import app.fintonic.demo.domain.models.response.IngredientListDto

class BeersMapper : BaseMapper<List<Beer>, List<BeerDto>>{

    override fun mapFrom(from: List<Beer>): List<BeerDto> {
        return from.map { transformBeer(it) }
    }

    private fun transformBeer(from: Beer) : BeerDto{
        return BeerDto(
            id = from.id,
            name = from.name,
            tagLine = from.tagLine,
            imageUrl = from.imageUrl,
            description = from.description,
            brewersTips = from.brewersTips,
            volume = transformAmount(from.volume),
            ingredients = transformIngredientList(from.ingredients)
        )
    }

    private fun transformAmount(from: Amount) : AmountDto{
        return AmountDto(
            value = from.value,
            unit = from.unit,
        )
    }

    private fun transformIngredient(from: Ingredient) : IngredientDto{
        return IngredientDto(
            name = from.name,
            amount = transformAmount(from.amount)
        )
    }

    private fun transformIngredientList(from: IngredientList) : IngredientListDto{
        return IngredientListDto(
            malt = from.malt.map { transformIngredient(it) },
            hops = from.hops.map { transformIngredient(it) }
        )
    }
}