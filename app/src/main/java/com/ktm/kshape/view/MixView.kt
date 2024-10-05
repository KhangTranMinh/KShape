package com.ktm.kshape.view

import android.content.Context
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import com.ktm.kshape.shape.Circle
import com.ktm.kshape.shape.Square
import com.ktm.kshape.shape.Triangle
import com.ktm.kshape.shape.base.Shape
import com.ktm.kshape.util.BitmapPool
import com.ktm.kshape.view.base.ShapeView

class MixView(
    context: Context, attrs: AttributeSet
) : ShapeView(context, attrs) {

    enum class ShapeType {
        SQUARE, CIRCLE, TRIANGLE
    }

    override val paint: Paint
        get() = Paint().apply {
            style = Paint.Style.FILL
        }

    override fun createShape(x: Float, y: Float): Shape {
        val shapeType = arrayOf(ShapeType.SQUARE, ShapeType.CIRCLE, ShapeType.TRIANGLE).random()
        return when (shapeType) {
            ShapeType.SQUARE -> Square(
                centerPoint = PointF(x, y),
                bitmap = BitmapPool.getBitmap(context)
            )

            ShapeType.CIRCLE -> Circle(centerPoint = PointF(x, y))

            ShapeType.TRIANGLE -> Triangle(centerPoint = PointF(x, y))
        }
    }
}