package com.example.macw

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddCourseActivity:AppCompatActivity() {
    lateinit var course: Course
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var db = Firebase.firestore
        setContentView(R.layout.edit_add_course)
        val BackButton=findViewById<Button>(R.id.back_btn)
        val AddCoursename=findViewById<EditText>(R.id.course_name)
        val AddCoursedescription=findViewById<EditText>(R.id.course_description)
        val AddCourseID=findViewById<EditText>(R.id.course_id)
        val Addteacher=findViewById<EditText>(R.id.course_teacher)
        val Add=findViewById<Button>(R.id.course_add)

       Add.setOnClickListener {

           val courseName=AddCoursename.text.toString()
           val coursedesciption=AddCoursedescription.text.toString()
           val courseID=AddCourseID.text.toString()
           val teacher=Addteacher.text.toString()
           course=Course(courseName,coursedesciption,courseID,teacher)
           db.collection("Course").add(course)

       }








        BackButton.setOnClickListener {
            val intent = Intent(this, EducatorActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}