package com.android.ssdam.sqLite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.ssdam.R
import java.text.Format

class diraryAdapter (mContext: Context, layout: Int, data: ArrayList<Diary>?) : BaseAdapter() {
    private var mContext: Context? = null
    private var layout = 0
    private var data: ArrayList<Diary>? = null
    private var inflater: LayoutInflater? = null

    init {
        this.mContext = mContext
        this.layout = layout
        this.data = data
        inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getCount(): Int {
       return  data!!.size
    }

    override fun getItem(position: Int): String? {
       return data!![position].insertDate
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertview = convertView
        if (convertview == null) {
            convertview = inflater!!.inflate(layout, parent, false)
        }
        val imageView: ImageView = convertview!!.findViewById(R.id.imageView)
        val tvTitle:TextView = convertview!!.findViewById(R.id.tv_title)
        val tvInsertDate:TextView = convertview!!.findViewById(R.id.tv_insertDate)

        tvTitle.text = data!![position].title
        tvInsertDate.text = "${(data!![position].insertDate)!!.subSequence(0,4)}.${(data!![position].insertDate)!!.subSequence(4,6)}.${(data!![position].insertDate)!!.subSequence(6,(data!![position].insertDate)!!.length)} "



        when(data!![position].imageFileName){
            "yellow" -> imageView.setImageResource(R.drawable.yellow)
            "green" -> imageView.setImageResource(R.drawable.green)
            "red" -> imageView.setImageResource(R.drawable.red)
            "orange" -> imageView.setImageResource(R.drawable.orange)
            "beige" -> imageView.setImageResource(R.drawable.beige)
            "laidGray" -> imageView.setImageResource(R.drawable.laidgray)
            "pink" ->  imageView.setImageResource(R.drawable.pink)
            "purple" ->  imageView.setImageResource(R.drawable.purple)
            "deepPurple" -> imageView.setImageResource(R.drawable.deeppurple)
            "liteBlue" ->  imageView.setImageResource(R.drawable.liteblue)
            "deepGray" -> imageView.setImageResource(R.drawable.deepgray)
            "navy" ->  imageView.setImageResource(R.drawable.navy)
        }

     return convertview
    }

}