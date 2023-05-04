package com.example.macw

data class EducatorData (  var emailAddress:String,
                           var password:String,
    //val courses: MutableList<Course> = mutableListOf()
)
data class Course(val name: String,
                  val description: String
)
data class Learner(val emailAddress: String,
                   val password: String,
    //val courses: MutableList<Course> = mutableListOf()
)
