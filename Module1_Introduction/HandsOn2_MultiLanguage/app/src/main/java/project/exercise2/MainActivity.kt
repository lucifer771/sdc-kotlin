package project.exercise2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        displayCurrentLocale()
    }

    private fun setupUI() {
        findViewById<Button>(R.id.changeLanguageButton).setOnClickListener {
            showLanguageChangeDialog()
        }
    }

    private fun displayCurrentLocale() {
        // Safe check: If no app-specific locale is set yet, use the system default
        val currentAppLocales = AppCompatDelegate.getApplicationLocales()
        val locale: Locale = if (!currentAppLocales.isEmpty) {
            currentAppLocales[0]!!
        } else {
            Locale.getDefault()
        }

        val languageTextView = findViewById<TextView>(R.id.languageTextView)

        // Formats to look like: "Current Language: English"
        // Ensure your strings.xml contains the placeholder if you want formatting
        val label = getString(R.string.current_language_label)
        languageTextView.text = "$label ${locale.displayName}"
    }

    private fun showLanguageChangeDialog() {
        val languages = arrayOf("English", "Español", "Français")
        val languageTags = arrayOf("en", "es", "fr")

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.change_language)) // Localized title
            .setItems(languages) { _, which ->
                val lang = languageTags[which]
                setAppLocale(lang)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setAppLocale(languageCode: String) {
        val appLocale = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
        // Activity recreates automatically
    }
}