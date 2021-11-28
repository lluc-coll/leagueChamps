package com.example.leaguechamps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ListOfChampsAdapter(private val champs: List<Champion>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ListOfChampsAdapter.ListOfChampsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfChampsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_champ,parent,false)

        return ListOfChampsViewHolder(view)
    }

    override fun onBindViewHolder(holderOfLists: ListOfChampsViewHolder, position: Int) {
        holderOfLists.bindData(champs[position])
    }

    override fun getItemCount(): Int {
        return champs.size
    }

    inner class ListOfChampsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var champIcon: ImageView
        private var champName: TextView

        init {
            champIcon = itemView.findViewById(R.id.champ_icon)
            champName = itemView.findViewById(R.id.champ_name)
        }

        fun bindData(champ: Champion){
            champIcon.setImageResource(champ.iconUrl)
            champName.text = champ.name

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}
