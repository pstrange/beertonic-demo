package app.fintonic.demo.domain.models.response

data class BeerDto(
    val idDb: Int = 0,
    val id: String = "",
    val name: String = "",
    val tagLine: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val brewersTips: String = "",
    val volume: AmountDto? = null,
    val ingredients: IngredientListDto? = null,
    var type: Int = 0,
)
