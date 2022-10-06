package app.fintonic.demo.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class Amount(
    @SerializedName("value")
    val value: String,
    @SerializedName("unit")
    val unit: String
)