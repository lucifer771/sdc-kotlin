package project.cameraapp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var btnCapture: ImageButton
    private lateinit var btnSwitch: ImageButton
    private lateinit var btnGallery: ImageButton
    private lateinit var btnFlash: ImageButton

    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var imageCapture: ImageCapture
    private var camera: Camera? = null

    private val cameraExecutor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        previewView = findViewById(R.id.previewView)
        btnCapture = findViewById(R.id.btnCapture)
        btnSwitch = findViewById(R.id.btnSwitch)
        btnGallery = findViewById(R.id.btnGallery)
        btnFlash = findViewById(R.id.btnFlash)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ),
            101
        )

        startCamera()

        btnCapture.setOnClickListener { takePhoto() }

        btnSwitch.setOnClickListener {
            cameraSelector =
                if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                    CameraSelector.DEFAULT_FRONT_CAMERA
                else
                    CameraSelector.DEFAULT_BACK_CAMERA

            startCamera()
        }

        btnGallery.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )

            startActivity(intent)
        }

        btnFlash.setOnClickListener {
            camera?.cameraControl?.enableTorch(true)
        }

        setupZoom()
    }

    private fun startCamera() {

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()

            preview.setSurfaceProvider(previewView.surfaceProvider)

            imageCapture = ImageCapture.Builder().build()

            cameraProvider.unbindAll()

            camera = cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageCapture
            )

        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {

        val photoFile = File(
            externalMediaDirs.first(),
            "${System.currentTimeMillis()}.jpg"
        )

        val outputOptions =
            ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),

            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(
                    output: ImageCapture.OutputFileResults
                ) {}

                override fun onError(
                    exception: ImageCaptureException
                ) {}
            }
        )
    }

    private fun setupZoom() {

        val scaleGestureDetector =
            ScaleGestureDetector(
                this,
                object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

                    override fun onScale(detector: ScaleGestureDetector): Boolean {

                        val zoomRatio =
                            camera?.cameraInfo?.zoomState?.value?.zoomRatio ?: 1f

                        camera?.cameraControl?.setZoomRatio(
                            zoomRatio * detector.scaleFactor
                        )

                        return true
                    }
                })

        previewView.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }
    }

}