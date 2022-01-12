package com.example.lab05_3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val imageURI = "https://upload.wikimedia.org/wikipedia/ru/thumb/0/08/Picasso01.jpg/480px-Picasso01.jpg"
    private val imageDownloader = ImageDownloader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<View>(R.id.imageView) as ImageView

        imageDownloader.downloadImage(imageURI) // Скачиваем картинку
        imageDownloader.getBitmapData().observe(this) { // Выводим картинку на экран
            imageView.setImageBitmap(it)
        }
    }
}
