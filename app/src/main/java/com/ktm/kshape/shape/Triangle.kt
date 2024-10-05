package com.ktm.kshape.shape

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import com.ktm.kshape.shape.base.Shape
import com.ktm.kshape.util.ColorPool

class Triangle(centerPoint: PointF) : Shape(centerPoint) {

    private val pointTop: PointF
        get() {
            val x = centerPoint.x
            val y = centerPoint.y - size
            return PointF(x, y)
        }

    private val pointLeft: PointF
        get() {
            val x = centerPoint.x - (0.866F * size)
            val y = centerPoint.y + (0.5F * size)
            return PointF(x, y)
        }

    private val pointRight: PointF
        get() {
            val x = centerPoint.x + (0.866F * size)
            val y = centerPoint.y + (0.5F * size)
            return PointF(x, y)
        }

    private val path = Path().apply {
        moveTo(pointTop.x, pointTop.y);
        lineTo(pointLeft.x, pointLeft.y);
        lineTo(pointRight.x, pointRight.y);
        lineTo(pointTop.x, pointTop.y);
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    override fun updateSelf(context: Context) {
        color = ColorPool.getColor()
    }
}