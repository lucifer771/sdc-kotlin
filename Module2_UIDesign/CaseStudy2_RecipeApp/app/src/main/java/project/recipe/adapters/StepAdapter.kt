package project.recipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.recipe.databinding.ItemStepBinding
import project.recipe.model.Step

class StepAdapter(private val list: List<Step>) :
    RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStepBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStepBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = list[position]
        holder.binding.stepNumber.text = "Step ${step.stepNumber}"
        holder.binding.stepDescription.text = step.description
    }
}