package com.devsurfer.domain.useCase.util

import android.content.Context
import android.graphics.Bitmap
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.util.StringUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.*
import java.lang.Exception
import javax.inject.Inject

class SaveBitmapToImageUseCase @Inject constructor(
    private val context: Context
){
    suspend operator fun invoke(bitmap: Bitmap): ResourceState<String> = withContext(Dispatchers.IO){
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
                ResourceState.Success(fileName)
            }catch (exception: IOException){
                ResourceState.Error(failure = Failure.UnHandleError())
            }catch (throwable: Throwable){
                ResourceState.Error(failure = Failure.UnHandleError())
            }catch (exception: Exception) {
                ResourceState.Error(failure = Failure.UnHandleError())
            }finally {
                    fos?.close()
            }
        }else{
            ResourceState.Error(failure = Failure.UnHandleError())
        }

    }
}