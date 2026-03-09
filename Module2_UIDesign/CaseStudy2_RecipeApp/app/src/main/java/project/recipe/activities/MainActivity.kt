package project.recipe.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONArray
import project.recipe.adapters.RecipeAdapter
import project.recipe.databinding.ActivityMainBinding
import project.recipe.model.Ingredient
import project.recipe.model.Recipe
import project.recipe.model.Step
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeList: MutableList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeList = loadRecipesFromJson()

        binding.recipeRecycler.layoutManager = LinearLayoutManager(this)
        binding.recipeRecycler.adapter = RecipeAdapter(recipeList)
    }

    private fun loadRecipesFromJson(): MutableList<Recipe> {

        val list = mutableListOf<Recipe>()

        val inputStream = assets.open("recipes.json")
        val reader = InputStreamReader(inputStream)
        val jsonText = reader.readText()

        val jsonArray = JSONArray(jsonText)

        for (i in 0 until jsonArray.length()) {

            val obj = jsonArray.getJSONObject(i)

            // 🔥 Parse Ingredients
            val ingredientsJson = obj.getJSONArray("ingredients")
            val ingredientsList = mutableListOf<Ingredient>()

            for (j in 0 until ingredientsJson.length()) {
                val ing = ingredientsJson.getJSONObject(j)
                ingredientsList.add(
                    Ingredient(
                        name = ing.getString("name"),
                        quantity = ing.getString("quantity")
                    )
                )
            }

            // 🔥 Parse Steps
            val stepsJson = obj.getJSONArray("steps")
            val stepsList = mutableListOf<Step>()

            for (k in 0 until stepsJson.length()) {
                val step = stepsJson.getJSONObject(k)
                stepsList.add(
                    Step(
                        stepNumber = step.getInt("stepNumber"),
                        description = step.getString("description")
                    )
                )
            }

            val recipe = Recipe(
                id = obj.getInt("id"),
                title = obj.getString("title"),
                description = obj.getString("description"),
                image = obj.getString("image"),
                prepTime = obj.getString("prepTime"),
                cookTime = obj.getString("cookTime"),
                serves = obj.getString("serves"),
                ingredients = ingredientsList,
                steps = stepsList
            )

            list.add(recipe)
        }

        return list
    }
}