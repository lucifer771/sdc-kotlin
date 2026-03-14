package project.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        val signin = findViewById<Button>(R.id.btnSignin)
        val signup = findViewById<Button>(R.id.btnSignup)

        signin.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

        }

        signup.setOnClickListener {

            startActivity(
                Intent(this, SignupActivity::class.java)
            )

        }

    }
}