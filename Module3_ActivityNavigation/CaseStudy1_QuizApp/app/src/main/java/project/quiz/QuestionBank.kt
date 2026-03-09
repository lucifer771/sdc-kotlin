package project.quiz

object QuestionBank {

    fun getQuestions(): List<Question> {

        return listOf(

            Question(
                "What is the capital of France?",
                listOf("London","Berlin","Paris","Madrid"),
                2
            ),

            Question(
                "Which planet is known as the Red Planet?",
                listOf("Venus","Mars","Jupiter","Saturn"),
                1
            ),

            Question(
                "What is 5 + 7?",
                listOf("10","11","12","13"),
                2
            ),

            Question(
                "Who developed Android?",
                listOf("Google","Microsoft","Apple","IBM"),
                0
            ),

            Question(
                "Which language is used for Android development?",
                listOf("Swift","Kotlin","Python","Ruby"),
                1
            )

        )
    }
}