package project.backgroundapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class DataUpdateWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        try {

            val apiUrl = URL("https://jsonplaceholder.typicode.com/posts")

            val connection = apiUrl.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode

            if (responseCode == 200) {

                val response = connection.inputStream.bufferedReader().readText()

                val jsonArray = JSONArray(response)

                val database = FirebaseDatabase.getInstance().reference.child("posts")

                for (i in 0 until jsonArray.length()) {

                    val post = jsonArray.getJSONObject(i)

                    val map = HashMap<String, Any>()

                    map["userId"] = post.getInt("userId")
                    map["id"] = post.getInt("id")
                    map["title"] = post.getString("title")
                    map["body"] = post.getString("body")

                    database.child(post.getInt("id").toString()).setValue(map)
                }

                Log.d("WORKER", "Data saved to Firebase")

                return Result.success()

            } else {

                return Result.failure()
            }

        } catch (e: Exception) {

            Log.e("WORKER ERROR", e.message.toString())

            return Result.failure()
        }
    }
}