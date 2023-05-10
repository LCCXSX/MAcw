package com.example.macw

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object Datas {
    var db = Firebase.firestore

    fun Data(){
    }
   fun getCourse(callback: (ArrayList<Course>) -> Unit) {
       val courseList= ArrayList<Course>()
      db.collection("Course").get().addOnSuccessListener{
          for(document in it){
              lateinit  var course:Course

              val name = document.data.get("name").toString()
              val courseID=document.data.get("courseID").toString()
              val description=document.data.get("description").toString()
              val teacher=document.data.get("teacher").toString()
              course=Course(name,courseID,description,teacher)
              Log.d("shuju","${document.data}")
              Log.d("xinshuju","${course.name}")
              courseList.add(course)
              Log.d("daxiao","${courseList.size}")
          }
          callback(courseList)
          Log.d("shulian","${ courseList.size}")
      }



        Log.d("zuiwaishulian","${ courseList.size}")

}
fun ShowCourse():ArrayList<Course>{
    val courseList=ArrayList<Course>()
    getCourse {
        for (item in it){
            courseList.add(item)
            Log.d("zuixunshuju","${ item.name}")
        }
    }
    Log.d("zuixinshulian","${courseList.size}")
    return courseList
}
}