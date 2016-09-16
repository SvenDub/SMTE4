package nl.svendubbeld.smte4

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.crime_list_item.view.*

class CrimeAdapter(val crimeList: List<Crime>) : RecyclerView.Adapter<CrimeAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCriminal(crimeList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crime_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = crimeList.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindCriminal(crime: Crime) {
            with(crime) {
                itemView.txt_name.text = name
                itemView.txt_bounty.text = bountyDisplay
                itemView.txt_description.text = description
            }
        }
    }
}