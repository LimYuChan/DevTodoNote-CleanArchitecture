package com.devsurfer.domain.useCase.util

import android.content.Context
import android.graphics.Bitmap
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.util.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

class SaveBitmapToImageUseCase @Inject constructor(
    private val context: Context
){
    operator fun invoke(bitmap: Bitmap): String?{
        val dir = context.externalCacheDir?.absoluteFile
        var fos: OutputStream? = null
        if(dir != null){
            try{
                if(dir.exists()){
                    dir.mkdirs()
                }
                val fileName = "${StringUtils.getNowTimeStamp()}.png"
                fos = FileOutputStream(File(dir, fileName))
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                return fileName
            }catch (exception: IOException){
                return null
            }catch (throwable: Throwable){
                return null
            }catch (exception: Exception) {
                return null
            }finally {
                fos?.close()
            }
        }else{
            return null
        }
    }
}