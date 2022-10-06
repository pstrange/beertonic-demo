package app.fintonic.demo.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: Amount)
