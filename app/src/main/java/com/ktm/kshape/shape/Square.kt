package com.ktm.kshape.shape

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import com.ktm.kshape.shape.base.Shape
import com.ktm.kshape.util.BitmapPool

class Square(
    centerPoint: PointF,
    bitmap: Bitmap? = null
) : Shape(
    centerPoint = centerPoint,
    color = Color.BLACK, // don't need to get random color for Square
    bitmap = bitmap
) {

    private val pointTopLeft: PointF
        get() {
            val x = centerPoint.x - size / 2
            val y = centerPoint.y - size / 2
            return PointF(x, y)
        }

    private val pointBottomRight: PointF
        get() {
            val x = centerPoint.x + size / 2
            val y = centerPoint.y + size / 2
            return PointF(x, y)
        }

    private val rectF = RectF(
        pointTopLeft.x, pointTopLeft.y, pointBottomRight.x, pointBottomRight.y
    )

    override fun draw(canvas: Canvas, paint: Paint) {
        bitmap?.let {
            canvas.drawBitmap(it, null, rectF, paint)
        } ?: {
            canvas.drawRect(rectF, paint)
        }
    }

    override fun updateSelf(context: Context) {
        bitmap = BitmapPool.getBitmap(context)
    }
}