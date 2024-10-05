package com.ktm.kshape.view.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.ktm.kshape.shape.base.Shape

abstract class ShapeView(
    context: Context, attrs: AttributeSet
) : View(context, attrs) {

    private val shapes = ArrayList<Shape>()

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            // create new shape
            shapes.add(createShape(event.x, event.y))
            invalidate()
            return true
        }

        override fun onDoubleTap(event: MotionEvent): Boolean {
            // if double touch on a shape, update color/bitmap
            val touchShape = shapes.findLast { it.isTouch(event.x, event.y) }
            if (touchShape != null) {
                touchShape.updateSelf(context)
                invalidate()
            }
            return true
        }

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
    }

    private val gestureDetector = GestureDetector(context, gestureListener).apply {
        setOnDoubleTapListener(gestureListener)
    }

    abstract fun createShape(x: Float, y: Float): Shape

    abstract val paint: Paint

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapes.forEach {
            it.draw(canvas, paint.apply { color = it.color })
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    fun removeAllShapes() {
        shapes.clear()
        invalidate()
    }
}