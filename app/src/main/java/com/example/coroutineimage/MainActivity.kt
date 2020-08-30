package com.example.coroutineimage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = URL("https://www.gstatic.com/webp/gallery/1.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val bitmap = getImage(url.toString())
                updateUI(bitmap)
            }
        }
    }

    private suspend fun getImage(url: String): Bitmap =
        withContext(Dispatchers.IO) {
            return@withContext BitmapFactory.decodeStream(URL(url).openStream())
        }


    private fun updateUI(bitmap: Bitmap) {
        Log.d("TAG", "UI function called")
        bitmap?.run {
            imageView.setImageBitmap(bitmap)
        }
    }
}