package project.page

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_success)

        val user = intent.getStringExtra("user")

        val successText = findViewById<TextView>(R.id.successText)
        val continueBtn = findViewById<Button>(R.id.continueBtn)

        successText.text = "Welcome $user 🎉\n\nYour account was successfully created!"

        continueBtn.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
    }
}