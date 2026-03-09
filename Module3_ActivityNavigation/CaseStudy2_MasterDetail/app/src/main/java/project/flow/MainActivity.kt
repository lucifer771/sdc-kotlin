package project.flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.flow.adapter.ContactAdapter
import project.flow.data.ContactData
import project.flow.model.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter
    private lateinit var searchView: SearchView

    private var contactList: List<Contact> = ContactData.getContacts()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerContacts)
        searchView = findViewById(R.id.searchContacts)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ContactAdapter(this, contactList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val filtered = contactList.filter {
                    it.name.contains(newText ?: "", true)
                }

                adapter.updateList(filtered)

                return true
            }
        })
    }
}