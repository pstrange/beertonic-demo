package app.fintonic.demo.domain.models.response

import java.lang.StringBuilder

data class IngredientListDto(
    val malt: List<IngredientDto>,
    val hops: List<IngredientDto>
){
    override fun toString(): String {
        val builder = StringBuilder()
            builder.append("Malt\n\n")
            malt.forEach {
                builder.append("- ${it.name} : ${it.amount.unit} ${it.amount.value}\n")
            }
            builder.append("\nHops\n\n")
            hops.forEach {
                builder.append("- ${it.name} : ${it.amount.unit} ${it.amount.value}\n")
            }
        return builder.toString()
    }
}