package project.recipe.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import project.recipe.adapters.IngredientAdapter
import project.recipe.adapters.StepAdapter
import project.recipe.databinding.ActivityRecipeDetailBinding
import project.recipe.model.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getSerializableExtra("recipe") as? Recipe

        if (recipe == null) {
            finish()
            return
        }

        binding.detailTitle.text = recipe.title
        binding.detailDescription.text = recipe.description

        val imageResId = resources.getIdentifier(
            recipe.image,
            "drawable",
            packageName
        )

        Glide.with(this)
            .load(imageResId)
            .centerCrop()
            .into(binding.detailImage)

        binding.ingredientRecycler.layoutManager = LinearLayoutManager(this)
        binding.ingredientRecycler.adapter = IngredientAdapter(recipe.ingredients)

        binding.stepRecycler.layoutManager = LinearLayoutManager(this)
        binding.stepRecycler.adapter = StepAdapter(recipe.steps)
    }
}