package com.example.shapes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class BoxDrawingView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var currentBox: Box? = null
    private val boxes = mutableListOf<Box>()
    private var shapeCounter = 0

    // paint classes specific characteristics
    private val boxPaint = Paint().apply {
        color = 0x22ff0000.toInt()
    }
    private val backgroundPaint = Paint().apply {
        color = 0xfff8efe0.toInt()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val current = PointF(event.x, event.y)

        when (event.action) {
            // press on the screen, so create a box
            MotionEvent.ACTION_DOWN -> {
                if (shapeCounter < 3) {
                    currentBox = Box(current).also {
                        boxes.add(it)
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                updateCurrentBox(current)
            }

            // pull up, done with the current box
            MotionEvent.ACTION_UP -> {
                updateCurrentBox(current)
                shapeCounter++
                currentBox = null
            }

            MotionEvent.ACTION_CANCEL -> {
                currentBox = null
            }
        }
        return true
    }

    private fun updateCurrentBox(current: PointF) {
        currentBox?.let {
            it.end = current
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPaint(backgroundPaint)
        boxes.forEach { box ->
            if (box.width >= box.height) {
                // draw square
                canvas.drawRect(
                    box.left,
                    box.top,
                    box.left + box.width,
                    box.top + box.width,
                    boxPaint
                )
            } else {
                // draw circle
                canvas.drawOval(
                    box.left,
                    box.top,
                    box.left + box.height,
                    box.top + box.height,
                    boxPaint
                )
            }
            //canvas.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
        }
    }
}
