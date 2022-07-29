package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.text1)
        textView.setText(OpenCVLoader.OPENCV_VERSION) // ★OpenCVのバージョンを設定
    }

    fun onButtonClick(view: View) {
        val imageView : ImageView = findViewById(R.id.imageView)

        val bitmap: Bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val srcMat : Mat = Mat()
        Utils.bitmapToMat(bitmap, srcMat)

        val grayMat = Mat()
        Imgproc.cvtColor(srcMat, grayMat, Imgproc.COLOR_BGR2GRAY)

        val binMat = Mat()
        Imgproc.threshold(grayMat, binMat, 0.0, 255.0, Imgproc.THRESH_OTSU)

        val resBitmap: Bitmap? = null
        Utils.matToBitmap(binMat, resBitmap)

        imageView.setImageBitmap(resBitmap)
    }


}