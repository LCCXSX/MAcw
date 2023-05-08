package com.example.macw

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Data {
    var db = Firebase.firestore
    fun Data(){
    }
fun AddCourse(course:Course){
    db.collection("Course").add(course)
}
}
data class Educator (  var emailAddress:String,
                        var password:String,
    //val courses: MutableList<Course> = mutableListOf()
)
data class Course(val name: String,
                  val description: String,
                  val courseID:String,
                  val teacher:String
)
data class Learner(val emailAddress: String,
                   val password: String,
    //val courses: MutableList<Course> = mutableListOf()
)