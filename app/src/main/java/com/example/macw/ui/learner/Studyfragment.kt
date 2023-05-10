package com.example.macw.ui.learner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macw.Course
import com.example.macw.Datas
import com.example.macw.R

class Studyfragment : Fragment(R.layout.fragment_learner){
    val courseList= Datas.ShowCourse()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler_view=view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recycler_view.adapter=StudyAdapter()
    }

    inner class StudyAdapter : RecyclerView.Adapter<StudyAdapter.StudyViewHolder>() {
        fun setDatas(){

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
            val view= LayoutInflater.from(context).inflate(R.layout.item_fragment_learner,parent,false)
            return StudyViewHolder(view)
        }

        override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
            val course: Course =courseList[position]
            holder.itemView.findViewById<ImageView>(R.id.item_course_poster).setImageResource(R.drawable.course)
            holder.itemView.findViewById<TextView>(R.id.item_course_title).text="[${position}]${course.name}"
            holder.itemView.findViewById<com.google.android.material.button.MaterialButton>(R.id.course_label).text="${course.description}"
            // holder.itemView.findViewById<TextView>(R.id.item_course_progress).text="已学习进度"
        }

        override fun getItemCount(): Int {
            return courseList.size
        }

        inner class StudyViewHolder(view:View):RecyclerView.ViewHolder(view){

        }

    }

}