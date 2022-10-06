package app.fintonic.demo.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class IngredientList(
    @SerializedName("malt")
    val malt: List<Ingredient>,
    @SerializedName("hops")
    val hops: List<Ingredient>
)