package com.android.ssdam.sqLite

class Diary(title: String, insertDate: String, imageFileName:String, content: String){

    var title: String? = null
    var insertDate: String? = null
    var imageFileName: String? = null
    var content: String? = null

    init {
        this.title = title
        this.insertDate = insertDate
        this.imageFileName = imageFileName
        this.content = content

    }



}
