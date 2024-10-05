package com.ktm.kshape.view

import android.content.Context
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import com.ktm.kshape.shape.Circle
import com.ktm.kshape.shape.base.Shape
import com.ktm.kshape.view.base.ShapeView

class CircleView(
    context: Context, attrs: AttributeSet
) : ShapeView(context, attrs) {

    override val paint: Paint
        get() = Paint().apply {
            style = Paint.Style.FILL
        }

    override fun createShape(x: Float, y: Float): Shape {
        return Circle(centerPoint = PointF(x, y))
    }
}