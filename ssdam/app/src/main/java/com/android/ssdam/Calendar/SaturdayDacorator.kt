package com.android.ssdam.Calendar


import android.app.Activity
import android.graphics.Color
import android.graphics.Color.WHITE
import android.graphics.Typeface
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import com.android.ssdam.R
import com.google.android.material.resources.TextAppearance
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.time.format.TextStyle
import java.util.*

class SaturdayDacorator: DayViewDecorator {

    private val calendar = Calendar.getInstance()


    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SATURDAY
    }

    override fun decorate(view: DayViewFacade?) {
         view?.addSpan(object : ForegroundColorSpan(Color.parseColor("#5186BA")) {})


    }

}


