package com.ktm.kshape.shape

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.ktm.kshape.shape.base.Shape
import com.ktm.kshape.util.ColorPool

class Circle(centerPoint: PointF) : Shape(centerPoint) {

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawCircle(centerPoint.x, centerPoint.y, size.toFloat(), paint)
    }

    override fun updateSelf(context: Context) {
        color = ColorPool.getColor()
    }
}