package com.example.jsondemo

import java.time.Duration
import kotlin.properties.Delegates

public class Model {
    var course_icon: Int ?= null
    lateinit var duration: String
    lateinit var fategory: String
    lateinit var fee: String
    lateinit var name: String
    constructor(course_image:Int, name: String,duration:String,fategory:String, fee:String) {
        this.name = name
        this.duration = duration
        this.fategory = fategory
        this.fee= fee
        this.course_icon= course_icon
    }

    constructor()
}