package nl.svendubbeld.smte4

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.criminal_list_item.view.*

class CriminalAdapter(val criminalsList: List<Criminal>, val itemClick: (Criminal, Int) -> Unit) : RecyclerView.Adapter<CriminalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCriminal(criminalsList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.criminal_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount() = criminalsList.size


    class ViewHolder(view: View, val itemClick: (Criminal, Int) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindCriminal(criminal: Criminal, position: Int) {
            with(criminal) {
                itemView.img_mugshot.setImageDrawable(mugshot)
                itemView.txt_name.text = name
                itemView.txt_bounty.text = bountyDisplay
                itemView.setOnClickListener { itemClick(this, position) }
            }
        }
    }
}