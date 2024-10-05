package com.ktm.kshape.shape.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.ktm.kshape.util.ColorPool
import com.ktm.kshape.util.getRandomShapeSize
import kotlin.math.sqrt

abstract class Shape(
    val centerPoint: PointF,
    val size: Int = getRandomShapeSize(),
    var color: Int = ColorPool.getColor(),
    var bitmap: Bitmap? = null
) {

    abstract fun draw(canvas: Canvas, paint: Paint)

    abstract fun updateSelf(context: Context)

    fun isTouch(x: Float, y: Float): Boolean {
        return size > distanceFromTouchPoint(x, y)
    }

    private fun distanceFromTouchPoint(x: Float, y: Float): Float {
        return sqrt(
            (y - centerPoint.y) * (y - centerPoint.y) + (x - centerPoint.x) * (x - centerPoint.x)
        )
    }
}