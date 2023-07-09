package com.example.myapplication2.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DetailsFragmentViewModel : ViewModel() {
    suspend fun loadWallpaper(urlString: String): Bitmap {
        return suspendCoroutine {
            val url = URL(urlString)
            val inputStream = url.openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            it.resume(bitmap)
        }
    }
}
