package ru.hanarlo.wheremymoneygoes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class RLadapter : Adapter<RLadapter.RLViewHolder>() {

    private var RLlist: List<itemRL> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RLViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view_r_l, parent, false)
        return RLViewHolder(itemView)
    }



    override fun getItemCount() = RLlist.size

    override fun onBindViewHolder(holder: RLViewHolder, position: Int) {
       val currentitem = RLlist[position]


        holder.textViewHM.text = currentitem.HowMuchText
        holder.textViewWhere.text = currentitem.WhereText
        holder.calendar.text = currentitem.calendar.toString()
        holder.color.setBackgroundColor(Color.parseColor(currentitem.BGcolor))
    }

    class RLViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewHM :TextView = itemView.findViewById(R.id.textViewHM)
        val textViewWhere: TextView = itemView.findViewById(R.id.textViewWhere)
        val calendar: TextView = itemView.findViewById(R.id.textViewCalendar)
        val color: RelativeLayout = itemView.findViewById(R.id.cardd)

    }

    fun setData(data: List<itemRL>){
        this.RLlist = data
        notifyDataSetChanged()
    }





}