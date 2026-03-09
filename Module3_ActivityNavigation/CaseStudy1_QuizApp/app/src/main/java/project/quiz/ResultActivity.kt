package project.quiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score",0)
        val total = intent.getIntExtra("total",5)

        val scoreText = findViewById<TextView>(R.id.scoreText)

        scoreText.text = "Your Score: $score / $total"
    }
}