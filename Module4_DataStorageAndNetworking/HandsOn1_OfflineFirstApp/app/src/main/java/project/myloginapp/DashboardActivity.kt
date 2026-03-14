package project.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val messageInput = findViewById<TextInputEditText>(R.id.messageInput)
        val sendBtn = findViewById<MaterialButton>(R.id.sendBtn)
        val logoutBtn = findViewById<MaterialButton>(R.id.logoutBtn)

        sendBtn.setOnClickListener {

            val message = messageInput.text.toString()

            if (message.isEmpty()) {
                Toast.makeText(this, "Enter message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = hashMapOf(
                "message" to message,
                "userEmail" to auth.currentUser?.email
            )

            firestore.collection("messages")
                .add(data)
                .addOnSuccessListener {

                    Toast.makeText(this, "Message Saved", Toast.LENGTH_SHORT).show()
                    messageInput.setText("")

                }
                .addOnFailureListener {

                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()

                }

        }

        logoutBtn.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}