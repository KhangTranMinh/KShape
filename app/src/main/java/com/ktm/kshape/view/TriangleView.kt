package com.ktm.kshape.view

import android.content.Context
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import com.ktm.kshape.shape.Triangle
import com.ktm.kshape.shape.base.Shape
import com.ktm.kshape.view.base.ShapeView

class TriangleView(
    context: Context, attrs: AttributeSet
) : ShapeView(context, attrs) {

    override val paint: Paint
        get() = Paint().apply {
            style = Paint.Style.FILL
        }

    override fun createShape(x: Float, y: Float): Shape {
        return Triangle(centerPoint = PointF(x, y))
    }
}