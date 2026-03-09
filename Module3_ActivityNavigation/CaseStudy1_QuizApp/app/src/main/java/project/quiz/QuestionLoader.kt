package project.quiz

import android.content.Context
import org.json.JSONArray

object QuestionLoader {

    fun loadQuestions(context: Context): List<Question> {

        val list = mutableListOf<Question>()

        val json = context.assets.open("questions.json")
            .bufferedReader()
            .use { it.readText() }

        val array = JSONArray(json)

        for (i in 0 until array.length()) {

            val obj = array.getJSONObject(i)

            val question = obj.getString("question")

            val optionsJson = obj.getJSONArray("options")

            val options = mutableListOf<String>()

            for (j in 0 until optionsJson.length()) {
                options.add(optionsJson.getString(j))
            }

            val answer = obj.getInt("answer")

            list.add(
                Question(
                    question,
                    options,
                    answer
                )
            )
        }

        return list
    }
}