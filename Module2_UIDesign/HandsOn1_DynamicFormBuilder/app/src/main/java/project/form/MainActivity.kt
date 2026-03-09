package project.form

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var formContainer: LinearLayout

    // Stores label + view reference for data collection
    private val inputViews = mutableListOf<Pair<String, Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formContainer = findViewById(R.id.formContainer)

        loadFormFromJson()
    }

    // 🔥 Load JSON and generate form
    private fun loadFormFromJson() {

        val jsonString = assets.open("form_config.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonObject = JSONObject(jsonString)
        val fields = jsonObject.getJSONArray("fields")

        for (i in 0 until fields.length()) {
            val field = fields.getJSONObject(i)
            createField(field)
        }

        createSubmitButton()
    }

    // 💎 Dynamic Field Creation
    private fun createField(field: JSONObject) {

        val type = field.getString("type")
        val label = field.getString("label")
        val required = field.optBoolean("required", false)

        // Card container
        val cardContainer = LinearLayout(this)
        cardContainer.orientation = LinearLayout.VERTICAL
        cardContainer.setPadding(40, 40, 40, 40)
        cardContainer.setBackgroundResource(R.drawable.glass_card)

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 50)
        cardContainer.layoutParams = params

        // Label
        val textLabel = TextView(this)
        textLabel.text = if (required) "$label *" else label
        textLabel.textSize = 18f
        textLabel.setTextColor(resources.getColor(android.R.color.white))
        textLabel.setPadding(0, 0, 0, 20)

        cardContainer.addView(textLabel)

        when (type) {

            "text", "email" -> {

                val editText = EditText(this)
                editText.hint = field.optString("hint")
                editText.setTextColor(resources.getColor(android.R.color.white))
                editText.setHintTextColor(resources.getColor(android.R.color.darker_gray))
                editText.setPadding(30, 30, 30, 30)
                editText.setBackgroundResource(android.R.color.transparent)

                if (type == "email") {
                    editText.inputType =
                        InputType.TYPE_CLASS_TEXT or
                                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }

                inputViews.add(Pair(label, editText))
                cardContainer.addView(editText)
            }

            "dropdown" -> {

                val spinner = Spinner(this)
                val options = field.getJSONArray("options")

                val list = ArrayList<String>()
                for (j in 0 until options.length()) {
                    list.add(options.getString(j))
                }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )

                spinner.adapter = adapter

                inputViews.add(Pair(label, spinner))
                cardContainer.addView(spinner)
            }

            "checkbox" -> {

                val checkBox = CheckBox(this)
                checkBox.text = label
                checkBox.setTextColor(resources.getColor(android.R.color.white))

                inputViews.add(Pair(label, checkBox))
                cardContainer.addView(checkBox)
            }
        }

        formContainer.addView(cardContainer)
    }

    // 🚀 Submit Button
    private fun createSubmitButton() {

        val submitButton = Button(this)
        submitButton.text = "SUBMIT FORM"
        submitButton.textSize = 20f
        submitButton.setTextColor(resources.getColor(android.R.color.white))
        submitButton.setBackgroundResource(R.drawable.neon_button)

        val buttonParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            160
        )
        buttonParams.setMargins(0, 80, 0, 80)
        submitButton.layoutParams = buttonParams

        submitButton.setOnClickListener {

            if (validateForm()) {

                val data = collectFormData()

                // Store in global storage
                DataStorage.submittedList.add(data)

                // Open Display Screen
                val intent = Intent(this, DisplayActivity::class.java)
                startActivity(intent)
            }
        }

        formContainer.addView(submitButton)
    }

    // ✅ Validation
    private fun validateForm(): Boolean {

        for ((label, view) in inputViews) {

            when (view) {

                is EditText -> {
                    if (view.text.toString().trim().isEmpty()) {
                        view.error = "$label is required"
                        return false
                    }
                }

                is CheckBox -> {
                    if (!view.isChecked) {
                        Toast.makeText(
                            this,
                            "Please accept $label",
                            Toast.LENGTH_SHORT
                        ).show()
                        return false
                    }
                }
            }
        }

        return true
    }

    // 📦 Collect Data Function (You Requested)
    private fun collectFormData(): SubmittedData {

        var name = ""
        var email = ""
        var department = ""
        var terms = false

        for ((label, view) in inputViews) {

            when (view) {

                is EditText -> {
                    if (label.contains("Name", true)) {
                        name = view.text.toString()
                    }
                    if (label.contains("Email", true)) {
                        email = view.text.toString()
                    }
                }

                is Spinner -> {
                    department = view.selectedItem.toString()
                }

                is CheckBox -> {
                    terms = view.isChecked
                }
            }
        }

        return SubmittedData(name, email, department, terms)
    }
}