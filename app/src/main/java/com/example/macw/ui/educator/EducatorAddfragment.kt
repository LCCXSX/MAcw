package com.example.macw.ui.educator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macw.R

class EducatorAddfragment :Fragment(R.layout.item_fragment_add_educator) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler_view=view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recycler_view.adapter=MyAdapter()
    }
    inner class MyAdapter :RecyclerView.Adapter<MyViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView:View= LayoutInflater.from(context).inflate(R.layout.item_educator_course,parent,false)

            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.findViewById<ImageView>(R.id.item_image).setImageResource(R.drawable.course)
            holder.itemView.findViewById<TextView>(R.id.item_title).text="[${position}]课程预览"
            holder.itemView.findViewById<TextView>(R.id.item_message).text="课程内容"
        }

    }
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

}