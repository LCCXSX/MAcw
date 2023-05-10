package com.example.macw.ui.educator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macw.AddCourseActivity
import com.example.macw.Course
import com.example.macw.Datas
import com.example.macw.R

class EducatorAddfragment :Fragment(R.layout.item_fragment_add_educator) {
  val courseList=Datas.ShowCourse()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler_view=view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recycler_view.adapter=MyAdapter()
        val addButton=view.findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(activity, AddCourseActivity::class.java)
            startActivity(intent)
        }
    }
    inner class MyAdapter :RecyclerView.Adapter<MyViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView:View= LayoutInflater.from(context).inflate(R.layout.item_educator_course,parent,false)

            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            Log.d("shuliang","${courseList.size}")
            return courseList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val course:Course=courseList[position]
            holder.itemView.findViewById<ImageView>(R.id.item_image).setImageResource(R.drawable.course)
            holder.itemView.findViewById<TextView>(R.id.item_title).text="[${position}]${course.name}"
            holder.itemView.findViewById<TextView>(R.id.item_message).text="${course.description}"
        }

    }
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

}