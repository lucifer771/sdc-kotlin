package project.stat // Important: Must match your actual package name

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import project.stat.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup Standard Web Links
        val tvWiki = findViewById<TextView>(R.id.tvWikiUrl)
        val tvGoogle = findViewById<TextView>(R.id.tvGoogleSearch)

        Linkify.addLinks(tvWiki, Linkify.WEB_URLS)
        Linkify.addLinks(tvGoogle, Linkify.WEB_URLS)

        // 2. Setup Google Maps App Trigger
        val tvMaps = findViewById<TextView>(R.id.tvMapsLink)
        tvMaps.setOnClickListener {
            // Geographic coordinates for Leh, Ladakh
            val uri = Uri.parse("geo:34.1526,77.5771?q=Leh+Ladakh")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            // Set package to force open Google Maps app specifically
            intent.setPackage("com.google.android.apps.maps")

            try {
                startActivity(intent)
            } catch (e: Exception) {
                // Fallback to browser if Maps app isn't installed
                val browserIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/Leh+Ladakh"))
                startActivity(browserIntent)
            }
        }
    }
}