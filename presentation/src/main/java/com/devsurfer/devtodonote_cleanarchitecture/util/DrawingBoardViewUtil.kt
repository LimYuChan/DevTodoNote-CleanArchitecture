package com.devsurfer.devtodonote_cleanarchitecture.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.devsurfer.devtodonote_cleanarchitecture.extension.resolveColorAttr
import com.devsurfer.domain.item.DrawingPoint
import com.esafirm.imagepicker.model.Image

@SuppressLint("ResourceType")
class DrawingBoardViewUtil(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var list = arrayListOf<DrawingPoint>()
    var nowColor: Int = 0

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint().apply {
            strokeWidth = 10f
        }
        list.forEachIndexed { index, point ->
            if(index > 1 && point.isContinue) {
                paint.color = list[index].color
                canvas?.drawLine(list[index-1].x, list[index-1].y, point.x, point.y, paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                list.add(DrawingPoint(event.x, event.y, false, nowColor))
            }
            MotionEvent.ACTION_MOVE->{
                list.add(DrawingPoint(event.x, event.y, true, nowColor))
            }
        }
        invalidate()
        return true
    }
    fun reset() {
        list.clear()
        invalidate()
    }
    fun removeLastPath(){
        if(list.isNotEmpty()){
            Log.d(TAG, "removeLastPath: $list")
            for(i in list.indices.reversed()){
                if(list[i].isContinue){
                    list.removeAt(i)
                }else{
                    list.removeAt(i)
                    break
                }
            }
            invalidate()
        }
    }

    fun getBitmap(): Bitmap{
        val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        this.draw(canvas)
        return bitmap
    }

    fun setPaintColor(color: Int){
        nowColor = color
    }

    fun getDrawing() : List<DrawingPoint> {
        return list
    }

    fun setDrawing(sign: List<DrawingPoint>) {
        list.addAll(sign)
        invalidate()
    }

    companion object{
        private const val TAG = "DrawingBoardViewUtil"
    }
}