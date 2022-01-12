package com.example.lab05_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageDownloader: ViewModel() {
    private val bitmapData = MutableLiveData<Bitmap>()
    private lateinit var job : Job
    fun getBitmapData() : LiveData<Bitmap> = bitmapData // Наружу отдаем неизменяемую LiveData - так безопаснее

    fun downloadImage(uri:String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            val imageURL = URL(uri)
            val bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream())
            withContext(Dispatchers.Main) { // Переключение в UI-поток
                bitmapData.value = bitmap
            }
        }
    }

    override fun onCleared() { // Вызывается перед уничтожением ViewModel - используем для остановки корутины
        job.cancel()
        super.onCleared()
    }
}