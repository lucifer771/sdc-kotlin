package project.hello

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LinearLayout
        val tvLinear = findViewById<TextView>(R.id.tvLinear)
        val btnLinear = findViewById<Button>(R.id.btnLinear)

        btnLinear.setOnClickListener {
            tvLinear.text = "LinearLayout Button Clicked!"
        }

        // RelativeLayout
        val tvRelative = findViewById<TextView>(R.id.tvRelative)
        val btnRelative = findViewById<Button>(R.id.btnRelative)

        btnRelative.setOnClickListener {
            tvRelative.text = "RelativeLayout Button Clicked!"
        }

        // ConstraintLayout
        val tvConstraint = findViewById<TextView>(R.id.tvConstraint)
        val btnConstraint = findViewById<Button>(R.id.btnConstraint)

        btnConstraint.setOnClickListener {
            tvConstraint.text = "ConstraintLayout Button Clicked!"
        }
    }
}
