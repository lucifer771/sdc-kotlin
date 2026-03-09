package project.recipe.model

import java.io.Serializable

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val prepTime: String,
    val cookTime: String,
    val serves: String,
    val ingredients: List<Ingredient>,
    val steps: List<Step>
) : Serializable