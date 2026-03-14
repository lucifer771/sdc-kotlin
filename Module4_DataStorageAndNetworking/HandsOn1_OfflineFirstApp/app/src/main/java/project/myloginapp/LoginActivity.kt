package project.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val mail = email.text.toString()
            val pass = password.text.toString()

            auth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        startActivity(
                            Intent(this, DashboardActivity::class.java)
                        )

                        finish()

                    } else {

                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()

                    }

                }

        }

    }
}