package project.implicit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var urlInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var subjectInput: TextInputEditText
    private lateinit var bodyInput: TextInputEditText
    private lateinit var shareInput: TextInputEditText

    private lateinit var openBrowserBtn: MaterialButton
    private lateinit var sendEmailBtn: MaterialButton
    private lateinit var shareTextBtn: MaterialButton
    private lateinit var openDialerBtn: MaterialButton
    private lateinit var openMapsBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // INPUT FIELDS
        urlInput = findViewById(R.id.inputUrl)
        emailInput = findViewById(R.id.inputEmail)
        subjectInput = findViewById(R.id.inputSubject)
        bodyInput = findViewById(R.id.inputBody)
        shareInput = findViewById(R.id.inputShareText)

        // BUTTONS
        openBrowserBtn = findViewById(R.id.btnOpenBrowser)
        sendEmailBtn = findViewById(R.id.btnSendEmail)
        shareTextBtn = findViewById(R.id.btnShareText)
        openDialerBtn = findViewById(R.id.btnDialer)
        openMapsBtn = findViewById(R.id.btnMaps)

        // OPEN WEBSITE
        openBrowserBtn.setOnClickListener {

            val url = urlInput.text.toString().trim()

            if (url.isEmpty()) {
                Snackbar.make(it, "Enter a website URL", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://$url")

            startActivity(intent)

            Snackbar.make(it, "Opening browser...", Snackbar.LENGTH_SHORT).show()
        }

        // SEND EMAIL
        sendEmailBtn.setOnClickListener {

            val email = emailInput.text.toString().trim()
            val subject = subjectInput.text.toString().trim()
            val body = bodyInput.text.toString().trim()

            if (email.isEmpty()) {
                Snackbar.make(it, "Enter email address", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val uri = Uri.parse(
                "mailto:$email?subject=" +
                        Uri.encode(subject) +
                        "&body=" +
                        Uri.encode(body)
            )

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = uri

            startActivity(Intent.createChooser(intent, "Send Email"))

            Snackbar.make(it, "Opening email app...", Snackbar.LENGTH_SHORT).show()
        }

        // SHARE TEXT
        shareTextBtn.setOnClickListener {

            val text = shareInput.text.toString().trim()

            if (text.isEmpty()) {
                Snackbar.make(it, "Enter text to share", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)

            startActivity(Intent.createChooser(intent, "Share via"))

            Snackbar.make(it, "Opening share menu...", Snackbar.LENGTH_SHORT).show()
        }

        // OPEN DIALER WITH 108
        openDialerBtn.setOnClickListener {

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:108")

            startActivity(intent)

            Snackbar.make(it, "Opening dialer with 108", Snackbar.LENGTH_SHORT).show()
        }

        // OPEN GOOGLE MAPS
        openMapsBtn.setOnClickListener {

            val mapUri = Uri.parse("geo:0,0?q=hospitals")

            val intent = Intent(Intent.ACTION_VIEW, mapUri)
            intent.setPackage("com.google.android.apps.maps")

            startActivity(intent)

            Snackbar.make(it, "Opening Google Maps...", Snackbar.LENGTH_SHORT).show()
        }
    }
}