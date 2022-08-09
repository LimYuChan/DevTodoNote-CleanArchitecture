package com.devsurfer.domain.useCase.util

import android.content.Context
import android.graphics.Bitmap
import com.devsurfer.domain.state.Failure
import com.devsurfer.domain.state.ResourceState
import com.devsurfer.domain.util.StringUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

class SaveBitmapToImageUseCase @Inject constructor(
    private val context: Context
){
    suspend operator fun invoke(bitmap: Bitmap): Flow<ResourceState<String>> = flow{
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
                emit(ResourceState.Success(fileName))
            }catch (exception: IOException){
                emit(ResourceState.Error(failure = Failure.UnHandleError()))
            }catch (throwable: Throwable){
                emit(ResourceState.Error(failure = Failure.UnHandleError()))
            }catch (exception: Exception) {
                emit(ResourceState.Error(failure = Failure.UnHandleError()))
            }finally {
                fos?.close()
            }
        }else{
            emit(ResourceState.Error(failure = Failure.UnHandleError()))
        }

    }
}