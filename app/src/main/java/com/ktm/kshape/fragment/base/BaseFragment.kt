package com.ktm.kshape.fragment.base

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ktm.kshape.view.base.ShapeView
import kotlin.math.sqrt


abstract class BaseFragment(contentLayoutId: Int) :
    Fragment(contentLayoutId),
    ShakeDetector.OnShakeListener {

    abstract val shapeView: ShapeView?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ShakeDetector().setOnShakeListener(this)
    }

    override fun onShake(count: Int) {
        shapeView?.removeAllShapes()
    }
}

class ShakeDetector : SensorEventListener {

    private var onShakeListener: OnShakeListener? = null
    private var shakeTimestamp: Long = 0
    private var shakeCount = 0

    fun setOnShakeListener(listener: OnShakeListener?) {
        this.onShakeListener = listener
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // ignore
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (onShakeListener != null) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // gForce will be close to 1 when there is no movement.
            val gForce =
                sqrt((gX * gX + gY * gY + gZ * gZ).toDouble()).toFloat()

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                val now = System.currentTimeMillis()
                // ignore shake events too close to each other (500ms)
                if (shakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return
                }

                // reset the shake count after 3 seconds of no shakes
                if (shakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    shakeCount = 0
                }

                shakeTimestamp = now
                shakeCount++

                onShakeListener!!.onShake(shakeCount)
            }
        }
    }

    interface OnShakeListener {
        fun onShake(count: Int)
    }

    companion object {
        private const val SHAKE_THRESHOLD_GRAVITY = 2.7F
        private const val SHAKE_SLOP_TIME_MS = 500
        private const val SHAKE_COUNT_RESET_TIME_MS = 3000
    }
}