package com.android.ssdam.Calendar

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.android.ssdam.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*
import kotlin.collections.ArrayList

class ColorDecorator(context: Activity, drawableID: Int, calday: ArrayList<CalendarDay>, color: ArrayList<String>) : DayViewDecorator{

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private val drawable: Drawable? = ContextCompat.getDrawable(context, drawableID)

    val days = calday
    private val calendar = Calendar.getInstance()
    var cday : Int = 0
    var count = days.size
    val colors = color

    override fun shouldDecorate(day: CalendarDay?): Boolean {
//





        if(count == 0){
            return false
        }else{
            println("들어오나?")
            println("day : ${day}")
            println("days : ${days}")
            println("result : ${day == days[0]}")
            println("개수 : ${days.size}")
            println("년, ${days[0].year}, 월 : ${(days[0].month)}, 일 : ${days[0].day}")
            println("변환 : ${days[0].date.time}")

            println("변환 : ${day?.date?.time}")

            for(i in 0..days.size) {
                count -= i
                cday = i


            }
            return days.contains(day)
            // (day?.day == days[cday].day) && (day?.month == (days[cday].month - 1)) && (day?.year == days[0].year)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun decorate(view: DayViewFacade?) {
        println("Selected Date decorate")
        if (drawable != null) {
            view?.setBackgroundDrawable(drawable)
        }
    }


}


