package project.recipe.model

import java.io.Serializable

data class Ingredient(
    val name: String,
    val quantity: String
) : Serializable