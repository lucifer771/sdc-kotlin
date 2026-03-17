package project.backgroundapp

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import androidx.work.*

import project.backgroundapp.worker.DataUpdateWorker

import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var btnOneTime: Button
    lateinit var btnPeriodic: Button
    lateinit var btnCancel: Button
    lateinit var progressBar: ProgressBar
    lateinit var txtStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnOneTime = findViewById(R.id.btnOneTime)
        btnPeriodic = findViewById(R.id.btnPeriodic)
        btnCancel = findViewById(R.id.btnCancel)

        progressBar = findViewById(R.id.progressBar)
        txtStatus = findViewById(R.id.txtStatus)

        btnOneTime.setOnClickListener {

            startOneTimeTask()
        }

        btnPeriodic.setOnClickListener {

            startPeriodicTask()
        }

        btnCancel.setOnClickListener {

            WorkManager.getInstance(this).cancelAllWork()

            txtStatus.text = "Status: All tasks cancelled"
        }
    }

    private fun startOneTimeTask() {

        val constraints = Constraints.Builder()

            .setRequiredNetworkType(NetworkType.CONNECTED)

            .build()

        val workRequest = OneTimeWorkRequestBuilder<DataUpdateWorker>()

            .setConstraints(constraints)

            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

        txtStatus.text = "Status: One-time update started"
    }

    private fun startPeriodicTask() {

        val constraints = Constraints.Builder()

            .setRequiredNetworkType(NetworkType.CONNECTED)

            .build()

        val periodicWork = PeriodicWorkRequestBuilder<DataUpdateWorker>(

            15,
            TimeUnit.MINUTES

        )

            .setConstraints(constraints)

            .build()

        WorkManager.getInstance(this).enqueue(periodicWork)

        txtStatus.text = "Status: Periodic update started"
    }
}