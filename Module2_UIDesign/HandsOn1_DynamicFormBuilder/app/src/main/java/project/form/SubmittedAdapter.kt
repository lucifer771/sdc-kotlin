package project.form

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubmittedAdapter(private val originalList: List<SubmittedData>) :
    RecyclerView.Adapter<SubmittedAdapter.ViewHolder>() {

    private var filteredList = originalList.toMutableList()

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val textView = TextView(parent.context)
        textView.setTextColor(Color.WHITE)
        textView.textSize = 18f
        textView.setPadding(40, 40, 40, 40)

        return ViewHolder(textView)
    }

    override fun getItemCount() = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = filteredList[position]

        holder.textView.text =
            "Name: ${data.fullName}\n" +
                    "Email: ${data.email}\n" +
                    "Department: ${data.department}\n" +
                    "Accepted Terms: ${data.acceptedTerms}"
    }

    fun filter(query: String) {

        filteredList = if (query.isEmpty()) {
            originalList.toMutableList()
        } else {
            originalList.filter {
                it.fullName.contains(query, ignoreCase = true)
            }.toMutableList()
        }

        notifyDataSetChanged()
    }
}