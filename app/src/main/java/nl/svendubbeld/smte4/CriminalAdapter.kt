package nl.svendubbeld.smte4

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.criminal_list_item.view.*
import java.text.NumberFormat

class CriminalAdapter(val criminalsList: List<Criminal>, val itemClick: (Criminal) -> Unit) : RecyclerView.Adapter<CriminalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCriminal(criminalsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.criminal_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount() = criminalsList.size


    class ViewHolder(view: View, val itemClick: (Criminal) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindCriminal(criminal: Criminal) {
            with(criminal) {
                itemView.img_mugshot.setImageDrawable(mugshot)
                itemView.txt_name.text = name
                itemView.txt_bounty.text = NumberFormat.getCurrencyInstance().format(bountyInDollars)
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}