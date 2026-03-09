package project.recipe.model

import java.io.Serializable

data class Step(
    val stepNumber: Int,
    val description: String
) : Serializable