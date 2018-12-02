package navin.sample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.customcell.view.*


class BatAdapter(val List: ArrayList<Bat>): RecyclerView.Adapter<BatAdapter.ViewHolder>()
{


    override fun getItemCount(): Int {
        return List.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customcell, parent, false)

        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: BatAdapter.ViewHolder, p1: Int) {
        val name = List[p1].currencyPair;
        val doller = "$" + String.format("%1$,.2f", List[p1].getLast());
        val in24h = String.format("%1$,.2f", List[p1].getBaseVolume()) + " USBT in 24h"
        val change = List[p1].getPercentChenge()




        viewHolder.name.text = name;
        viewHolder.dollar.text = doller;
        viewHolder.change.text =String.format("%1$,.2f",change)
        viewHolder.in24h.text = in24h;
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.name
        val change = v.change
        val in24h = v.bst
        val dollar = v.dollar

    }
}

