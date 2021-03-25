package com.android.ssdam.sqLite

import android.icu.text.CaseMap

class Diary{

    var title: String? = null
    var insertDate: String? = null
    var imageFileName: String? = null
    var content: String? = null

    init {
    }


    constructor(insertDate: String){
        this.insertDate = insertDate
    }

    constructor(insertDate: String, imageFileName: String) : this(insertDate) {

        this.insertDate = insertDate
        this.imageFileName = imageFileName

    }

    constructor(title: String, insertDate: String, imageFileName: String, content: String) : this(insertDate){

        this.title = title
        this.insertDate = insertDate
        this.imageFileName = imageFileName
        this.content = content
    }


}
