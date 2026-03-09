package project.page

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var loginEmailEt: TextInputEditText
    private lateinit var loginPasswordEt: TextInputEditText
    private lateinit var loginBtn: Button

    private lateinit var fullNameEt: TextInputEditText
    private lateinit var registerEmailEt: TextInputEditText
    private lateinit var registerPasswordEt: TextInputEditText
    private lateinit var confirmPasswordEt: TextInputEditText
    private lateinit var termsCheckBox: CheckBox
    private lateinit var registerBtn: Button

    private lateinit var loginLayout: LinearLayout
    private lateinit var registerLayout: LinearLayout
    private lateinit var loginTab: Button
    private lateinit var registerTab: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupTabs()
        setupLogin()
        setupRegister()
    }

    private fun initViews() {
        loginLayout = findViewById(R.id.loginLayout)
        registerLayout = findViewById(R.id.registerLayout)
        loginTab = findViewById(R.id.loginTab)
        registerTab = findViewById(R.id.registerTab)

        loginEmailEt = findViewById(R.id.loginEmailEt)
        loginPasswordEt = findViewById(R.id.loginPasswordEt)
        loginBtn = findViewById(R.id.loginBtn)

        fullNameEt = findViewById(R.id.fullNameEt)
        registerEmailEt = findViewById(R.id.registerEmailEt)
        registerPasswordEt = findViewById(R.id.registerPasswordEt)
        confirmPasswordEt = findViewById(R.id.confirmPasswordEt)
        termsCheckBox = findViewById(R.id.termsCheckBox)
        registerBtn = findViewById(R.id.registerBtn)
    }

    private fun setupTabs() {
        loginTab.setOnClickListener {
            loginLayout.visibility = View.VISIBLE
            registerLayout.visibility = View.GONE
        }

        registerTab.setOnClickListener {
            loginLayout.visibility = View.GONE
            registerLayout.visibility = View.VISIBLE
        }
    }

    private fun setupLogin() {
        loginBtn.setOnClickListener {
            if (validateLogin()) {

                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("user", loginEmailEt.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun setupRegister() {
        registerBtn.setOnClickListener {
            if (validateRegister()) {

                val intent = Intent(this, RegisterSuccessActivity::class.java)
                intent.putExtra("user", fullNameEt.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun validateLogin(): Boolean {
        val email = loginEmailEt.text.toString().trim()
        val password = loginPasswordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmailEt.error = "Invalid Email"
            return false
        }

        if (password.length < 6) {
            loginPasswordEt.error = "Password must be 6+ characters"
            return false
        }

        return true
    }

    private fun validateRegister(): Boolean {
        val name = fullNameEt.text.toString().trim()
        val email = registerEmailEt.text.toString().trim()
        val password = registerPasswordEt.text.toString().trim()
        val confirmPassword = confirmPasswordEt.text.toString().trim()

        if (name.isEmpty()) {
            fullNameEt.error = "Name required"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmailEt.error = "Invalid Email"
            return false
        }

        if (password.length < 6) {
            registerPasswordEt.error = "Password must be 6+ characters"
            return false
        }

        if (password != confirmPassword) {
            confirmPasswordEt.error = "Passwords do not match"
            return false
        }

        if (!termsCheckBox.isChecked) {
            Toast.makeText(this, "Accept Terms first", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}