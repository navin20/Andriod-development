package com.shota.android.readjsonfromapi

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.customcell.view.*
import android.widget.Toast



class DogAdapter(val studentList: ArrayList<Dog>):RecyclerView.Adapter<DogAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customcell,parent,false)
/*        v.setOnClickListener{
            var message = v.subtitle.text +":"+ v.title.text
            Toast.makeText(it,message, Toast.LENGTH_LONG).show()
        }*/
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: DogAdapter.ViewHolder, p1: Int) {
        var name = studentList[p1].name;
        viewHolder.studentName.text = name;
        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, studentList[p1].id+": "+studentList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            //Toast.makeText(it.context,id+":"+name, Toast.LENGTH_LONG).show()
            val intent = Intent(viewHolder.itemView.context,ImageActivity::class.java).apply {
                putExtra("name",name);
            }
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val studentName = v.title
    }
}