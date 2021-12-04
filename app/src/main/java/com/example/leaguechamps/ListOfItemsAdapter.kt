package com.example.leaguechamps

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ListOfItemsAdapter(private val items: List<Item>, private val listener: ListOfChampsAdapter.OnItemClickListener) : RecyclerView.Adapter<ListOfItemsAdapter.ListOfItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return ListOfItemsViewHolder(view)
    }

    override fun onBindViewHolder(holderOfLists: ListOfItemsViewHolder, position: Int) {
        holderOfLists.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ListOfItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var itemIcon: ImageView
        private var itemName: TextView
        private var itemDesc: TextView
        private var itemGold: TextView

        init {
            itemIcon = itemView.findViewById(R.id.item_icon)
            itemName = itemView.findViewById(R.id.item_name)
            itemDesc = itemView.findViewById(R.id.item_desc)
            itemGold = itemView.findViewById(R.id.item_gold)

        }

        fun bindData(item: Item){
            Picasso.get().load(item.icon).into(itemIcon);
            itemName.text = item.name
            itemDesc.text = item.desc
            itemGold.text = item.gold.toString()
        }
        override fun onClick(v: View?) {}
    }
}