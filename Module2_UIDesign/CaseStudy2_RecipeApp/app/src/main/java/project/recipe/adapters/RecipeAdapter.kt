package project.recipe.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.recipe.activities.RecipeDetailActivity
import project.recipe.databinding.ItemRecipeBinding
import project.recipe.model.Recipe

class RecipeAdapter(private val recipeList: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val recipe = recipeList[position]
        val context = holder.itemView.context

        holder.binding.title.text = recipe.title
        holder.binding.description.text = recipe.description

        val imageResId = context.resources.getIdentifier(
            recipe.image,
            "drawable",
            context.packageName
        )

        Glide.with(context)
            .load(imageResId)
            .centerCrop()
            .into(holder.binding.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            context.startActivity(intent)
        }
    }
}