package project.flow.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.flow.DetailActivity
import project.flow.R
import project.flow.model.Contact

class ContactAdapter(
    private val context: Context,
    private var contactList: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val avatar: ImageView = itemView.findViewById(R.id.contactAvatar)
        val name: TextView = itemView.findViewById(R.id.contactName)
        val phone: TextView = itemView.findViewById(R.id.contactPhone)
        val company: TextView = itemView.findViewById(R.id.contactCompany)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_contact, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val contact = contactList[position]

        holder.name.text = contact.name
        holder.phone.text = contact.phone
        holder.company.text = contact.company

        holder.avatar.setImageResource(R.drawable.ic_launcher_foreground)

        holder.itemView.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("contact", contact)

            context.startActivity(intent)

        }
    }

    // 🔎 Used for search filtering
    fun updateList(newList: List<Contact>) {

        contactList = newList
        notifyDataSetChanged()

    }
}