package com.android.ssdam.Calendar

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class CustomMultipleDotSpan : LineBackgroundSpan {
    private val DEFAULT_RADIUS: Float = 20f
    private var colors : ArrayList<Int> = ArrayList()
    private var radius = 0f

    constructor(colors : ArrayList<Int>){
        this.radius = DEFAULT_RADIUS
        this.colors = colors
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, left: Int, right: Int, top: Int, baseline: Int, bottom: Int, text: CharSequence, start: Int, end: Int, lineNumber: Int) {
        val total = if(colors.size > 2) 3 else colors.size
        var leftMost = (total - 1) * - 12

        for(i in 0 until total){
            val oldColor = paint.color
            if(colors[i] != 0){
                paint.color = colors[i]
            }
            canvas.drawCircle(((left + right) / 2 - leftMost).toFloat(), bottom + radius, radius, paint)
            paint.color = oldColor
            leftMost += 24
        }
    }
}