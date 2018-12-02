package com.shota.android.btpnavjson

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.customcell.view.*
import android.widget.Toast



class DataAdapter(val dataList: ArrayList<Data>):RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return dataList.size
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

    override fun onBindViewHolder(viewHolder: DataAdapter.ViewHolder, p1: Int) {
        var date = dataList[p1].date;
        var data1 = dataList[p1].preTaxNav.toString()
        var data2 = dataList[p1].preTaxBid.toString()
        viewHolder.date.text = date;
        viewHolder.data1.text = data1;
        viewHolder.data2.text = data2;
        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, dataList[p1].id+": "+dataList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            Toast.makeText(it.context,data1+":"+date, Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val date = v.date
        val data1 = v.data1
        val data2 = v.data2
    }
}