package project.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>

    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questions = QuestionBank.getQuestions()

        loadQuestion()
    }

    private fun loadQuestion() {

        if (currentIndex >= questions.size) {

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("total", questions.size)

            startActivity(intent)
            finish()

            return
        }

        val question = questions[currentIndex]

        val questionText = findViewById<TextView>(R.id.questionText)
        val option1 = findViewById<RadioButton>(R.id.option1)
        val option2 = findViewById<RadioButton>(R.id.option2)
        val option3 = findViewById<RadioButton>(R.id.option3)
        val option4 = findViewById<RadioButton>(R.id.option4)

        val radioGroup = findViewById<RadioGroup>(R.id.optionsGroup)
        val submitBtn = findViewById<Button>(R.id.submitBtn)

        questionText.text = question.question

        option1.text = question.options[0]
        option2.text = question.options[1]
        option3.text = question.options[2]
        option4.text = question.options[3]

        radioGroup.clearCheck()

        submitBtn.setOnClickListener {

            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {

                Toast.makeText(this,"Select an answer",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton = findViewById<RadioButton>(selectedId)

            val correctButton = when(question.correctAnswer){

                0 -> option1
                1 -> option2
                2 -> option3
                else -> option4
            }

            if(selectedButton == correctButton){
                score++
            }

            currentIndex++

            loadQuestion()
        }
    }
}