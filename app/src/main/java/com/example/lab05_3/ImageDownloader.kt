package com.example.lab05_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageDownloader: ViewModel() {
    private val bitmapData = MutableLiveData<Bitmap>()
    fun getBitmapData() : LiveData<Bitmap> = bitmapData // Наружу отдаем неизменяемую LiveData - так безопаснее

    fun downloadImage(uri:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val imageURL = URL(uri)
            val bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream())
            bitmapData.postValue(bitmap)
        }
    }
}