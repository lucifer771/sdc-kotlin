package project.recipe.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import project.recipe.model.Recipe
import java.io.InputStreamReader

object JsonParser {

    fun loadRecipes(context: Context): List<Recipe> {
        val inputStream = context.assets.open("recipes.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Recipe>>() {}.type
        return Gson().fromJson(reader, type)
    }
}