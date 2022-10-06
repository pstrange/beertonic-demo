package app.fintonic.demo.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagLine: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("brewers_tips")
    val brewersTips: String,
    @SerializedName("volume")
    val volume: Amount,
    @SerializedName("ingredients")
    val ingredients: IngredientList
)
