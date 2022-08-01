package com.devsurfer.domain.useCase.util

import android.content.Context
import android.graphics.Bitmap
import com.devsurfer.domain.util.StringUtils
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class SaveBitmapToImageUseCase @Inject constructor(
    private val context: Context
){
    operator fun invoke(bitmap: Bitmap): String? = runBlocking {
        val dir = context.externalCacheDir?.absoluteFile
        if(dir != null){
            if(dir.exists()){
                dir.mkdirs()
            }
            val fileName = "${StringUtils.getNowTimeStamp()}.png"
            val fos = FileOutputStream(File(dir, fileName))
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()
            fileName
        }else{
            null
        }
    }
}