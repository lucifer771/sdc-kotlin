package project.flow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import project.flow.model.Contact

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val contact = intent.getSerializableExtra("contact") as? Contact

        if (contact == null) {
            finish()
            return
        }

        val name = findViewById<TextView>(R.id.detailName)
        val phone = findViewById<TextView>(R.id.detailPhone)
        val email = findViewById<TextView>(R.id.detailEmail)
        val company = findViewById<TextView>(R.id.detailCompany)
        val address = findViewById<TextView>(R.id.detailAddress)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnEmail = findViewById<Button>(R.id.btnEmail)
        val btnWhatsapp = findViewById<Button>(R.id.btnWhatsapp)

        name.text = contact.name
        phone.text = "📞 ${contact.phone}"
        email.text = "✉ ${contact.email}"
        company.text = "🏢 ${contact.company}"
        address.text = "📍 ${contact.address}"

        // CALL
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${contact.phone}")
            startActivity(intent)
        }

        // EMAIL
        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${contact.email}")
            startActivity(intent)
        }

        // WHATSAPP
        btnWhatsapp.setOnClickListener {
            val uri = Uri.parse("https://wa.me/${contact.phone.replace("+","").replace(" ","")}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}